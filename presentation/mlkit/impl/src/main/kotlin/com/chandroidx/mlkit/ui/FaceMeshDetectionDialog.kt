package com.chandroidx.mlkit.ui

import android.graphics.PointF
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import com.chandroidx.mlkit.MlKitNavKey
import com.chandroidx.mlkit.util.drawPointsWithPolygon
import com.chandroidx.mlkit.util.drawRect
import com.github.skydoves.navgraph.annotations.NavDestination
import com.google.mlkit.vision.facemesh.FaceMesh
import com.google.mlkit.vision.facemesh.FaceMeshDetection
import com.google.mlkit.vision.facemesh.FaceMeshDetectorOptions

@Composable
@NavDestination(route = MlKitNavKey.FaceMeshDetection::class)
internal fun FaceMeshDetectionDialog(
  modifier: Modifier = Modifier,
) {
  val detectedFaceMeshes = remember { mutableStateListOf<FaceMesh>() }

  val options = FaceMeshDetectorOptions
    .Builder()
    .setUseCase(FaceMeshDetectorOptions.FACE_MESH)
    .build()

  val detector = remember { FaceMeshDetection.getClient(options) }

  Box(
    modifier = modifier
      .aspectRatio(1f)
      .fillMaxWidth(),
  ) {
    CameraPreview(
      modifier = Modifier.fillMaxSize(),
      detector = detector,
    ) { result ->
      result.getValue(detector)?.let { faceMeshes ->
        detectedFaceMeshes.clear()
        detectedFaceMeshes.addAll(faceMeshes)
      }
    }

    Box(
      modifier = Modifier
        .fillMaxSize()
        .clipToBounds()
        .drawWithContent {
          detectedFaceMeshes.forEach { faceMesh ->
            drawRect(faceMesh.boundingBox)

            faceMesh.allTriangles!!.forEach { triangle ->
              drawPointsWithPolygon(
                points = triangle!!.allPoints.map {
                  PointF(it.position.x, it.position.y)
                },
                lineColor = Color.White,
                polygonWidth = 1f,
                pointWidth = 3f,
              )
            }
          }
        },
    )
  }
}
