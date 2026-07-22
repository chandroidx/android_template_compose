package com.chandroidx.mlkit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.chandroidx.mlkit.MlKitNavKey
import com.chandroidx.mlkit.util.drawLine
import com.chandroidx.mlkit.util.drawPoints
import com.github.skydoves.navgraph.annotations.NavDestination
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions

@Composable
@NavDestination(route = MlKitNavKey.PoseDetection::class)
internal fun PoseDetectionDialog(
  modifier: Modifier = Modifier,
) {
  var detectedPose by remember { mutableStateOf<Pose?>(null) }

  val options = PoseDetectorOptions
    .Builder()
    .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
    .build()

  val detector = remember { PoseDetection.getClient(options) }

  Box(
    modifier = modifier
      .aspectRatio(1f)
      .fillMaxWidth(),
  ) {
    CameraPreview(
      modifier = Modifier.fillMaxSize(),
      detector = detector,
    ) { result ->
      result.getValue(detector)?.let { pose ->
        detectedPose = pose
      }
    }

    if (detectedPose != null) {
      fun DrawScope.drawLine(
        @PoseLandmark.LandmarkType from: Int,
        @PoseLandmark.LandmarkType to: Int,
      ) {
        drawLine(
          start = detectedPose!!.getPoseLandmark(from)?.position,
          end = detectedPose!!.getPoseLandmark(to)?.position,
        )
      }

      Box(
        modifier = Modifier
          .fillMaxSize()
          .clipToBounds()
          .drawWithContent {
            drawPoints(
              points = detectedPose!!.leftPoseLandmarks().map(PoseLandmark::getPosition),
              pointColor = Color(0xFF5FC9D6),
              strokeWidth = 8f,
            )

            drawPoints(
              points = detectedPose!!.rightPoseLandmarks().map(PoseLandmark::getPosition),
              pointColor = Color(0xFFD0914A),
              strokeWidth = 8f,
            )

            drawLine(from = PoseLandmark.NOSE, to = PoseLandmark.LEFT_EYE_INNER)
            drawLine(from = PoseLandmark.LEFT_EYE_INNER, to = PoseLandmark.LEFT_EYE)
            drawLine(from = PoseLandmark.LEFT_EYE, to = PoseLandmark.LEFT_EYE_OUTER)
            drawLine(from = PoseLandmark.LEFT_EYE_OUTER, to = PoseLandmark.LEFT_EAR)

            drawLine(from = PoseLandmark.NOSE, to = PoseLandmark.RIGHT_EYE_INNER)
            drawLine(from = PoseLandmark.RIGHT_EYE_INNER, to = PoseLandmark.RIGHT_EYE)
            drawLine(from = PoseLandmark.RIGHT_EYE, to = PoseLandmark.RIGHT_EYE_OUTER)
            drawLine(from = PoseLandmark.RIGHT_EYE_OUTER, to = PoseLandmark.RIGHT_EAR)

            drawLine(from = PoseLandmark.LEFT_SHOULDER, to = PoseLandmark.RIGHT_SHOULDER)
            drawLine(from = PoseLandmark.LEFT_SHOULDER, to = PoseLandmark.LEFT_ELBOW)
            drawLine(from = PoseLandmark.LEFT_ELBOW, to = PoseLandmark.LEFT_WRIST)
            drawLine(from = PoseLandmark.LEFT_WRIST, to = PoseLandmark.LEFT_THUMB)
            drawLine(from = PoseLandmark.LEFT_WRIST, to = PoseLandmark.LEFT_INDEX)
            drawLine(from = PoseLandmark.LEFT_WRIST, to = PoseLandmark.LEFT_PINKY)
            drawLine(from = PoseLandmark.LEFT_INDEX, to = PoseLandmark.LEFT_PINKY)
            drawLine(from = PoseLandmark.RIGHT_SHOULDER, to = PoseLandmark.RIGHT_ELBOW)
            drawLine(from = PoseLandmark.RIGHT_ELBOW, to = PoseLandmark.RIGHT_WRIST)
            drawLine(from = PoseLandmark.RIGHT_WRIST, to = PoseLandmark.RIGHT_THUMB)
            drawLine(from = PoseLandmark.RIGHT_WRIST, to = PoseLandmark.RIGHT_INDEX)
            drawLine(from = PoseLandmark.RIGHT_WRIST, to = PoseLandmark.RIGHT_PINKY)
            drawLine(from = PoseLandmark.RIGHT_INDEX, to = PoseLandmark.RIGHT_PINKY)

            drawLine(from = PoseLandmark.LEFT_SHOULDER, to = PoseLandmark.LEFT_HIP)
            drawLine(from = PoseLandmark.RIGHT_SHOULDER, to = PoseLandmark.RIGHT_HIP)
            drawLine(from = PoseLandmark.LEFT_HIP, to = PoseLandmark.RIGHT_HIP)

            drawLine(from = PoseLandmark.LEFT_HIP, to = PoseLandmark.LEFT_KNEE)
            drawLine(from = PoseLandmark.LEFT_KNEE, to = PoseLandmark.LEFT_ANKLE)
            drawLine(from = PoseLandmark.LEFT_ANKLE, to = PoseLandmark.LEFT_HEEL)
            drawLine(from = PoseLandmark.LEFT_ANKLE, to = PoseLandmark.LEFT_FOOT_INDEX)
            drawLine(from = PoseLandmark.LEFT_HEEL, to = PoseLandmark.LEFT_FOOT_INDEX)

            drawLine(from = PoseLandmark.RIGHT_HIP, to = PoseLandmark.RIGHT_KNEE)
            drawLine(from = PoseLandmark.RIGHT_KNEE, to = PoseLandmark.RIGHT_ANKLE)
            drawLine(from = PoseLandmark.RIGHT_ANKLE, to = PoseLandmark.RIGHT_HEEL)
            drawLine(from = PoseLandmark.RIGHT_ANKLE, to = PoseLandmark.RIGHT_FOOT_INDEX)
            drawLine(from = PoseLandmark.RIGHT_HEEL, to = PoseLandmark.RIGHT_FOOT_INDEX)
          },
      )
    }
  }
}

private fun Pose.leftPoseLandmarks() = allPoseLandmarks.filter {
  it.landmarkType in arrayOf(
    PoseLandmark.LEFT_EYE_INNER,
    PoseLandmark.LEFT_EYE,
    PoseLandmark.LEFT_EYE_OUTER,
    PoseLandmark.LEFT_EAR,
    PoseLandmark.LEFT_MOUTH,
    PoseLandmark.LEFT_SHOULDER,
    PoseLandmark.LEFT_ELBOW,
    PoseLandmark.LEFT_WRIST,
    PoseLandmark.LEFT_PINKY,
    PoseLandmark.LEFT_INDEX,
    PoseLandmark.LEFT_THUMB,
    PoseLandmark.LEFT_HIP,
    PoseLandmark.LEFT_KNEE,
    PoseLandmark.LEFT_ANKLE,
    PoseLandmark.LEFT_HEEL,
    PoseLandmark.LEFT_FOOT_INDEX,
  )
}

private fun Pose.rightPoseLandmarks() = allPoseLandmarks.filter {
  it.landmarkType in arrayOf(
    PoseLandmark.RIGHT_EYE_INNER,
    PoseLandmark.RIGHT_EYE,
    PoseLandmark.RIGHT_EYE_OUTER,
    PoseLandmark.RIGHT_EAR,
    PoseLandmark.RIGHT_MOUTH,
    PoseLandmark.RIGHT_SHOULDER,
    PoseLandmark.RIGHT_ELBOW,
    PoseLandmark.RIGHT_WRIST,
    PoseLandmark.RIGHT_PINKY,
    PoseLandmark.RIGHT_INDEX,
    PoseLandmark.RIGHT_THUMB,
    PoseLandmark.RIGHT_HIP,
    PoseLandmark.RIGHT_KNEE,
    PoseLandmark.RIGHT_ANKLE,
    PoseLandmark.RIGHT_HEEL,
    PoseLandmark.RIGHT_FOOT_INDEX,
  )
}
