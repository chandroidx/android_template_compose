package com.chandroidx.home.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chandroidx.home.HomeNavKey
import com.chandroidx.home.SecondNavKey
import com.chandroidx.home.model.HomeIntent
import com.chandroidx.home.model.HomeSideEffect
import com.chandroidx.home.viewmodel.HomeViewModel
import com.chandroidx.navigator.LocalNavigator
import com.chandroidx.navigator.LocalResultStore
import com.chandroidx.presentation.theme.ApplicationTheme
import com.github.skydoves.navgraph.annotations.NavDestination
import com.github.skydoves.navgraph.annotations.NavEdge
import com.github.skydoves.navgraph.annotations.NavGraphRoot
import com.github.skydoves.navgraph.annotations.NavPreview
import kotlin.random.Random

@Composable
@NavGraphRoot
@NavDestination(route = HomeNavKey::class)
@NavEdge(to = SecondNavKey::class)
fun HomeScreen(viewModel: HomeViewModel) {
  val navigator = LocalNavigator.current
  val resultStore = LocalResultStore.current
  val context = LocalContext.current

  val result by resultStore.getResultState<SecondNavKey.Result>()

  LaunchedEffect(result) {
    if (result != null) {
      Toast.makeText(context, result!!.value.toString(), Toast.LENGTH_SHORT).show()
    }

    resultStore.removeResult<SecondNavKey.Result>()
  }

  LaunchedEffect(Unit) {
    viewModel.sideEffect.collect { sideEffect ->
      when (sideEffect) {
        HomeSideEffect.NavigateToSecondScreen -> navigator.navigate(SecondNavKey(Random.nextInt()))
      }
    }
  }

  HomeScreen(
    onIntent = viewModel::onIntent,
  )
}

@Composable
private fun HomeScreen(
  onIntent: (HomeIntent) -> Unit,
) {
  ApplicationTheme {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.Center),
        horizontalArrangement = Arrangement.SpaceEvenly,
      ) {
        SimpleButton(
          text = "SecondScreen",
          onClick = {
            onIntent(HomeIntent.OnSecondScreenButtonClicked)
          },
        )
      }
    }
  }
}

@Composable
private fun SimpleButton(
  onClick: () -> Unit,
  text: String,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier
      .border(width = 1.dp, color = Color.Black)
      .background(Color.White)
      .clickable(onClick = onClick)
      .padding(10.dp),
  ) {
    Text(
      text = text,
      color = Color.Black,
    )
  }
}

@Preview
@Composable
@NavPreview(route = HomeNavKey::class)
private fun HomeScreenPreview() {
  HomeScreen(
    onIntent = {},
  )
}
