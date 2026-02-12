package com.chandroidx.mlkit.ui

import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Color
import android.graphics.Matrix
import androidx.annotation.ColorInt
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.google.mlkit.vision.segmentation.Segmentation
import com.google.mlkit.vision.segmentation.SegmentationMask
import com.google.mlkit.vision.segmentation.selfie.SelfieSegmenterOptions

@Composable
internal fun SelfieSegmentationDialog(
  modifier: Modifier = Modifier,
) {
  val options = SelfieSegmenterOptions
    .Builder()
    .setDetectorMode(SelfieSegmenterOptions.STREAM_MODE)
    .build()

  val segmenter = remember { Segmentation.getClient(options) }
  var segmentedMask by remember { mutableStateOf<SegmentationMask?>(null) }

  Box(
    modifier = modifier
      .aspectRatio(1f)
      .fillMaxWidth(),
  ) {
    var previewSize by remember { mutableStateOf<IntSize?>(null) }

    CameraPreview(
      modifier = Modifier
        .fillMaxSize()
        .onSizeChanged { size ->
          previewSize = size
        },
      requireLensFacing = CameraSelector.LENS_FACING_FRONT,
      targetCoordinateSystem = ImageAnalysis.COORDINATE_SYSTEM_ORIGINAL, // Segmentation only works with COORDINATE_SYSTEM_ORIGINAL
      detector = segmenter,
    ) { result ->
      result.getValue(segmenter)?.let { segmentationMask ->
        segmentedMask = segmentationMask
      }
    }

    if (segmentedMask != null && previewSize != null) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .drawBehind {
            val scaleX = previewSize!!.width.toFloat() / segmentedMask!!.width
            val scaleY = previewSize!!.height.toFloat() / segmentedMask!!.height
            val matrix = Matrix().apply {
              preScale(-1f, 1f)
              postTranslate(segmentedMask!!.width.toFloat(), 0f)
              postScale(scaleX, scaleY)
            }

            val bitmap = createBitmap(segmentedMask!!.maskColors(), segmentedMask!!.width, segmentedMask!!.height, Bitmap.Config.ARGB_8888)
            drawIntoCanvas { canvas ->
              canvas.nativeCanvas.drawBitmap(bitmap, matrix, null)
            }

            bitmap.recycle()
          },
      )
    }
  }
}

@ColorInt
private fun SegmentationMask.maskColors(): IntArray {
  @ColorInt val colors = IntArray(width * height)

  for (i in 0 until width * height) {
    val foregroundLikelihood = buffer.float
    if (foregroundLikelihood > 0.9) {
      colors[i] = Color.argb(128, 255, 0, 255)
    } else if (foregroundLikelihood > 0.2) {
      val alpha = (182.9 * foregroundLikelihood - 36.6 + 0.5).toInt()
      colors[i] = Color.argb(alpha, 255, 0, 255)
    }
  }

  return colors
}
