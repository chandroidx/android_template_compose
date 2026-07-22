package com.chandroidx.home.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chandroidx.home.SecondNavKey
import com.chandroidx.home.model.SecondState
import com.chandroidx.home.viewmodel.SecondViewModel
import com.chandroidx.navigator.LocalNavigator
import com.chandroidx.navigator.LocalResultStore
import com.chandroidx.presentation.theme.ApplicationTheme
import com.github.skydoves.navgraph.annotations.NavDestination
import com.github.skydoves.navgraph.annotations.NavPreview

@Composable
@NavDestination(route = SecondNavKey::class)
fun SecondScreen(
  viewModel: SecondViewModel,
) {
  val navigator = LocalNavigator.current
  val state by viewModel.state.collectAsStateWithLifecycle()
  val resultStore = LocalResultStore.current

  BackHandler {
    resultStore.setResult(result = SecondNavKey.Result(state.value))
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

@Preview
@Composable
@NavPreview(route = SecondNavKey::class)
private fun SecondScreenPreview() {
  SecondScreen(
    state = SecondState(4),
  )
}
