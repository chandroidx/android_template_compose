package com.chandroidx.mlkit.ui

import android.Manifest
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.util.Consumer
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.interfaces.Detector

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun CameraPreview(
  detector: Detector<*>,
  modifier: Modifier = Modifier,
  requireLensFacing: Int = CameraSelector.LENS_FACING_BACK,
  targetCoordinateSystem: Int = ImageAnalysis.COORDINATE_SYSTEM_VIEW_REFERENCED,
  consumer: Consumer<MlKitAnalyzer.Result> = Consumer { _ -> },
) {
  val context = LocalContext.current
  val lifecycleOwner = LocalLifecycleOwner.current

  val cameraController = remember {
    LifecycleCameraController(context).apply {
      setImageAnalysisAnalyzer(
        ContextCompat.getMainExecutor(context),
        MlKitAnalyzer(
          listOf(detector),
          targetCoordinateSystem,
          ContextCompat.getMainExecutor(context),
          consumer,
        ),
      )

      cameraSelector = CameraSelector
        .Builder()
        .requireLensFacing(requireLensFacing)
        .build()

      bindToLifecycle(lifecycleOwner)
    }
  }

  val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

  if (cameraPermissionState.status.isGranted) {
    AndroidView(
      modifier = modifier.clipToBounds(),
      factory = {
        PreviewView(context).apply {
          scaleType = PreviewView.ScaleType.FILL_CENTER
          implementationMode = PreviewView.ImplementationMode.COMPATIBLE
          controller = cameraController
        }
      },
      onRelease = {
        detector.close()
        cameraController.unbind()
      },
    )
  } else {
    Box(
      modifier = modifier
        .clickable {
          cameraPermissionState.launchPermissionRequest()
        },
    ) {
      Text(
        modifier = Modifier.align(Alignment.Center),
        text = "Camera permission is required\nTap to grant permission",
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
      )
    }
  }
}
