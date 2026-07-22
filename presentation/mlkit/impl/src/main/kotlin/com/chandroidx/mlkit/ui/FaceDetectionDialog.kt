package com.chandroidx.mlkit.ui

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
import androidx.core.graphics.ColorUtils
import com.chandroidx.mlkit.MlKitNavKey
import com.chandroidx.mlkit.util.drawPointsWithPolygon
import com.chandroidx.mlkit.util.drawRect
import com.github.skydoves.navgraph.annotations.NavDestination
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

@Composable
@NavDestination(route = MlKitNavKey.FaceDetection::class)
internal fun FaceDetectionDialog(
  modifier: Modifier = Modifier,
) {
  val detectedFaces = remember { mutableStateListOf<Face>() }

  val options = FaceDetectorOptions
    .Builder()
    // High-accuracy landmark detection and face classification
    .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
    .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
    .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
    // Real-time contour detection
    .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
    .build()

  val detector = remember { FaceDetection.getClient(options) }

  Box(
    modifier = modifier
      .aspectRatio(1f)
      .fillMaxWidth(),
  ) {
    CameraPreview(
      modifier = Modifier.fillMaxSize(),
      detector = detector,
    ) { result ->
      result.getValue(detector)?.let { faces ->
        detectedFaces.clear()
        detectedFaces.addAll(faces)
      }
    }

    fun colorFromNumberHsl(number: Int): Color {
      val hue = (number * 60f) % 360f
      val hsl = floatArrayOf(hue, 0.6f, 0.5f)
      val colorInt = ColorUtils.HSLToColor(hsl)
      return Color(colorInt)
    }

    Box(
      modifier = Modifier
        .fillMaxSize()
        .clipToBounds()
        .drawWithContent {
          detectedFaces.forEach { face ->
            drawRect(face.boundingBox)

            face.allContours.forEach { contours ->
              drawPointsWithPolygon(
                contours.points,
                lineColor = colorFromNumberHsl(contours.faceContourType),
              )
            }
          }
        },
    )
  }
}
