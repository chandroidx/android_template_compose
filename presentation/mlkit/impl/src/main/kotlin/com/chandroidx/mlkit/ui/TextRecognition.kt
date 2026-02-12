package com.chandroidx.mlkit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

@Composable
fun TextRecognition(
  modifier: Modifier = Modifier,
) {
  val detector = remember { TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS) }
  var textRecognitionResult by remember { mutableStateOf("") }

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(20.dp),
  ) {
    CameraPreview(
      modifier = Modifier
        .aspectRatio(1f)
        .fillMaxWidth()
        .align(Alignment.CenterHorizontally),
      detector = detector,
    ) { result ->
      result.getValue(detector)?.let {
        textRecognitionResult = it.text
      }
    }

    if (textRecognitionResult.isNotEmpty()) {
      Text(
        text = textRecognitionResult,
        color = MaterialTheme.colorScheme.primary,
      )
    }
  }
}
