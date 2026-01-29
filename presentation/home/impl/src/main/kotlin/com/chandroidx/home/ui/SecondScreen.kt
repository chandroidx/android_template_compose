package com.chandroidx.home.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.chandroidx.home.SecondNavKey
import com.chandroidx.home.model.SecondState
import com.chandroidx.home.viewmodel.SecondViewModel
import com.chandroidx.navigator.LocalNavigator
import com.chandroidx.navigator.LocalResultStore
import com.chandroidx.presentation.theme.ApplicationTheme

@Composable
fun SecondScreen(
  viewModel: SecondViewModel,
) {
  val navigator = LocalNavigator.current
  val state by viewModel.state.collectAsState()
  val resultStore = LocalResultStore.current

  BackHandler {
    resultStore.setResult(resultKey = SecondNavKey.RESULT_KEY, result = state.value)
    navigator.navigateUp()
  }

  SecondScreen(
    state = state,
  )
}

@Composable
private fun SecondScreen(
  state: SecondState,
) {
  ApplicationTheme {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
    ) {
      Text(
        modifier = Modifier.align(Alignment.Center),
        text = state.value.toString(),
        color = Color.White,
      )
    }
  }
}

@Composable
@Preview
private fun SecondScreenPreview() {
  SecondScreen(
    state = SecondState(4),
  )
}
