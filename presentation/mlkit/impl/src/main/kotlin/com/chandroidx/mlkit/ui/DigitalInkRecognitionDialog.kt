package com.chandroidx.mlkit.ui

import android.os.SystemClock
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.chandroidx.mlkit.MlKitNavKey
import com.github.skydoves.navgraph.annotations.NavDestination
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.vision.digitalink.recognition.DigitalInkRecognition
import com.google.mlkit.vision.digitalink.recognition.DigitalInkRecognitionModel
import com.google.mlkit.vision.digitalink.recognition.DigitalInkRecognitionModelIdentifier
import com.google.mlkit.vision.digitalink.recognition.DigitalInkRecognizerOptions
import com.google.mlkit.vision.digitalink.recognition.Ink
import kotlin.collections.forEach

@Composable
@NavDestination(route = MlKitNavKey.DigitalInkRecognition::class)
internal fun DigitalInkRecognitionDialog(
  modifier: Modifier = Modifier,
) {
  val modelIdentifier = remember { DigitalInkRecognitionModelIdentifier.fromLanguageTag("en-US") }
  val model = remember { DigitalInkRecognitionModel.builder(modelIdentifier!!).build() }
  val recognizer = remember { DigitalInkRecognition.getClient(DigitalInkRecognizerOptions.builder(model).build()) }

  val remoteModelManager = remember { RemoteModelManager.getInstance() }
  val isModelDownloaded by rememberModelReady(remoteModelManager, model)

  val strokes = remember { mutableStateListOf<SnapshotStateList<Ink.Point>>() }
  val currentStroke = remember { mutableStateListOf<Ink.Point>() }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .background(MaterialTheme.colorScheme.background),
    verticalArrangement = Arrangement.spacedBy(5.dp),
  ) {
    InkCanvas(
      modifier = Modifier
        .aspectRatio(1f)
        .fillMaxWidth()
        .border(1.dp, MaterialTheme.colorScheme.primary),
      strokes = strokes,
      currentStroke = currentStroke,
    )

    val context = LocalContext.current
    ButtonsRow(
      canRecognize = isModelDownloaded,
      onRecognizeClick = {
        recognizer
          .recognize(buildInk(strokes + listOf(currentStroke)))
          .addOnSuccessListener { result ->
            result.candidates.firstOrNull()?.let { candidate ->
              Toast.makeText(context, candidate.text, Toast.LENGTH_SHORT).show()
            }
          }
      },
      onClearClick = {
        strokes.clear()
        currentStroke.clear()
      },
    )
  }
}

@Composable
private fun InkCanvas(
  strokes: SnapshotStateList<SnapshotStateList<Ink.Point>>,
  currentStroke: SnapshotStateList<Ink.Point>,
  modifier: Modifier = Modifier,
) {
  val primaryColor = MaterialTheme.colorScheme.primary

  Box(
    modifier = modifier
      .pointerInput(Unit) {
        awaitEachGesture {
          val down = awaitFirstDown()

          currentStroke.clear()
          currentStroke.add(down.position.toInkPoint())

          drag(down.id) { change ->
            currentStroke.add(change.position.toInkPoint())
            change.consume()
          }

          if (currentStroke.isNotEmpty()) {
            strokes.add(currentStroke)
            currentStroke.clear()
          }
        }
      },
  ) {
    Canvas(
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f),
    ) {
      fun drawStroke(points: List<Ink.Point>) {
        if (points.isEmpty()) return

        if (points.size == 1) {
          drawCircle(
            color = primaryColor,
            radius = 10f,
            center = Offset(points[0].x, points[0].y),
          )
          return
        }

        for (i in 0 until points.size - 1) {
          drawLine(
            color = primaryColor,
            start = Offset(points[i].x, points[i].y),
            end = Offset(points[i + 1].x, points[i + 1].y),
            strokeWidth = 10f,
            cap = StrokeCap.Round,
          )
        }
      }

      strokes.forEach(::drawStroke)
      drawStroke(currentStroke)
    }
  }
}

@Composable
private fun rememberModelReady(
  remoteModelManager: RemoteModelManager,
  model: DigitalInkRecognitionModel,
): State<Boolean> = produceState(initialValue = false, remoteModelManager, model) {
  remoteModelManager.isModelDownloaded(model).addOnCompleteListener { taskResult ->
    if (!taskResult.result) {
      remoteModelManager
        .download(model, DownloadConditions.Builder().build())
        .addOnSuccessListener {
          value = true
        }
    } else {
      value = true
    }
  }
}

@Composable
private fun ButtonsRow(
  canRecognize: Boolean,
  onRecognizeClick: () -> Unit,
  onClearClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier.wrapContentHeight(),
    horizontalArrangement = Arrangement.spacedBy(5.dp),
  ) {
    TextButton(
      modifier = Modifier.weight(1f),
      enabled = canRecognize,
      text = "Recognize",
      onClick = onRecognizeClick,
    )
    TextButton(
      modifier = Modifier.weight(1f),
      text = "Clear",
      onClick = onClearClick,
    )
  }
}

private fun Offset.toInkPoint(): Ink.Point = Ink.Point.create(x, y, SystemClock.uptimeMillis())

private fun buildInk(
  strokes: List<List<Ink.Point>>,
): Ink {
  val inkBuilder = Ink.builder()

  strokes.forEach { points ->
    if (points.isEmpty()) return@forEach
    val strokeBuilder = Ink.Stroke.builder()
    points.forEach(strokeBuilder::addPoint)
    inkBuilder.addStroke(strokeBuilder.build())
  }

  return inkBuilder.build()
}
