package com.deepfine.naver.ui

import android.Manifest
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chandroidx.buildconfig.BuildConfig
import com.deepfine.naver.util.SimpleRecognitionListener
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.naver.speech.clientapi.SpeechConfig
import com.naver.speech.clientapi.SpeechRecognitionResult
import com.naver.speech.clientapi.SpeechRecognizer

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpeechRecognitionDialog() {
  val context = LocalContext.current
  val recognizer = remember { SpeechRecognizer(context, BuildConfig.NAVER_API_CLIENT_ID) }

  val recordAudioPermissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)

  if (recordAudioPermissionState.status.isGranted) {
    var result by remember { mutableStateOf("") }
    var audio by remember { mutableStateOf<ShortArray?>(null) }

    DisposableEffect(recognizer) {
      recognizer.initialize()
      recognizer.setSpeechRecognitionListener(
        object : SimpleRecognitionListener() {
          override fun onRecord(speech: ShortArray?) {
            audio = speech
          }

          override fun onPartialResult(partialResult: String?) {
            result = partialResult.orEmpty()
            super.onPartialResult(partialResult)
          }

          override fun onResult(finalResult: SpeechRecognitionResult?) {
            result = finalResult?.results?.firstOrNull().toString()
          }
        },
      )

      recognizer.recognize(
        SpeechConfig(
          // languageType =
          SpeechConfig.LanguageType.KOREAN,
          // endPointDetectType =
          SpeechConfig.EndPointDetectType.AUTO,
        ),
      )

      onDispose {
        recognizer.release()
      }
    }

    Column(
      modifier = Modifier,
    ) {
      Waveform(
        modifier = Modifier.size(200.dp),
        audio = audio,
      )

      Text(
        text = result,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
      )
    }
  } else {
    Box(
      modifier = Modifier
        .clickable {
          recordAudioPermissionState.launchPermissionRequest()
        },
    ) {
      Text(
        modifier = Modifier.align(Alignment.Center),
        text = "Record audio permission is required\nTap to grant permission",
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
      )
    }
  }
}

@Composable
private fun Waveform(
  audio: ShortArray?,
  modifier: Modifier = Modifier,
) {
  Canvas(modifier = modifier) {
    if (audio == null || audio.isEmpty()) return@Canvas

    val widthPerSample = size.width / audio.size
    val centerY = size.height / 2

    audio.forEachIndexed { index, sample ->
      val normalized = sample / Short.MAX_VALUE.toFloat()
      val lineHeight = normalized * centerY

      drawLine(
        color = Color.Black,
        start = Offset(
          x = index * widthPerSample,
          y = centerY - lineHeight,
        ),
        end = Offset(
          x = index * widthPerSample,
          y = centerY + lineHeight,
        ),
        strokeWidth = 2f,
      )
    }
  }
}
