package com.chandroidx.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chandroidx.presentation.theme.ApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val LocalSnackbar = compositionLocalOf<SnackbarState> {
  error("No Navigator found!")
}

@Composable
fun rememberSnackbarState(): SnackbarState = remember {
  SnackbarState()
}

@Composable
fun BoxScope.Snackbar(
  state: SnackbarState,
  modifier: Modifier = Modifier,
) {
  var latestMessage by remember { mutableStateOf("") }
  val newMessage = state.message
  if (newMessage != null) {
    latestMessage = newMessage
  }

  ApplicationTheme {
    AnimatedVisibility(
      modifier = modifier
        .align(Alignment.BottomCenter)
        .fillMaxWidth()
        .navigationBarsPadding(),
      visible = state.message != null,
      enter = fadeIn() + slideInVertically(
        initialOffsetY = { fullHeight -> fullHeight },
      ),
      exit = fadeOut() + slideOutVertically(
        targetOffsetY = { fullHeight -> fullHeight },
      ),
    ) {
      Box(
        modifier = Modifier
          .padding(20.dp)
          .dropShadow(
            shape = RoundedCornerShape(10.dp),
            Shadow(
              radius = 3.dp,
              color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
              spread = 1.dp,
            ),
          ).background(shape = RoundedCornerShape(10.dp), color = MaterialTheme.colorScheme.background)
          .padding(horizontal = 20.dp, vertical = 10.dp),
      ) {
        Text(
          text = latestMessage,
          color = MaterialTheme.colorScheme.primary,
          fontSize = 10.sp,
          lineHeight = 12.sp,
        )
      }
    }
  }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun SnackbarPreview() {
  ApplicationTheme {
    Box(modifier = Modifier.fillMaxSize()) {
      Snackbar(rememberSnackbarState())
    }
  }
}

@Stable
class SnackbarState {
  var message by mutableStateOf<String?>(null)
    private set

  private var hideJob: Job? = null

  fun show(scope: CoroutineScope, message: String, durationMillis: Long = DURATION_MILLIS) {
    this.message = message
    hideJob?.cancel()
    hideJob = scope.launch {
      delay(durationMillis)
      this@SnackbarState.message = null
    }
  }

  companion object {
    private const val DURATION_MILLIS = 3000L
  }
}
