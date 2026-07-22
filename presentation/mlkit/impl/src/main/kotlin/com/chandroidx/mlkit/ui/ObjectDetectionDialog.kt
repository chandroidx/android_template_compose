package com.chandroidx.mlkit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chandroidx.mlkit.MlKitNavKey
import com.chandroidx.mlkit.util.drawRect
import com.github.skydoves.navgraph.annotations.NavDestination
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions

@Composable
@NavDestination(route = MlKitNavKey.ObjectDetectionAndTracking::class)
internal fun ObjectDetectionDialog(
  modifier: Modifier = Modifier,
) {
  val options = ObjectDetectorOptions
    .Builder()
    .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
    .enableClassification()
    .build()

  val objectDetector = remember { ObjectDetection.getClient(options) }

  var objectDetectionResult by remember { mutableStateOf<DetectedObject?>(null) }

  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(20.dp),
  ) {
    Box(
      modifier = Modifier
        .aspectRatio(1f)
        .fillMaxWidth(),
    ) {
      CameraPreview(
        modifier = Modifier.fillMaxSize(),
        detector = objectDetector,
      ) { result ->
        result.getValue(objectDetector)?.let { detectedObjects ->
          objectDetectionResult = detectedObjects.firstOrNull()
        }
      }

      if (objectDetectionResult != null) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
            .drawWithContent {
              drawRect(objectDetectionResult!!.boundingBox)
            },
        )
      }
    }

    val label = objectDetectionResult
      ?.labels
      ?.takeIf {
        it.isNotEmpty()
      }?.maxBy { label ->
        label.confidence
      }?.text

    if (!label.isNullOrEmpty()) {
      Text(
        text = label,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
      )
    }
  }
}
