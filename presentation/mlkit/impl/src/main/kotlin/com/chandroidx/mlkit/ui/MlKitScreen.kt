package com.chandroidx.mlkit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chandroidx.core.Component
import com.chandroidx.core.ui.ComponentColumn
import com.chandroidx.core.ui.Header
import com.chandroidx.mlkit.MlKitApi
import com.chandroidx.mlkit.R
import com.chandroidx.mlkit.model.MlKitIntent
import com.chandroidx.mlkit.model.MlKitSideEffect
import com.chandroidx.mlkit.model.MlKitState
import com.chandroidx.mlkit.viewmodel.MlKitViewModel
import com.chandroidx.navigator.LocalNavigator
import com.chandroidx.presentation.theme.ApplicationTheme

@Composable
fun MlKitScreen(
  viewModel: MlKitViewModel,
) {
  val state by viewModel.state.collectAsState()

  val navigator = LocalNavigator.current

  LaunchedEffect(Unit) {
    viewModel.sideEffect.collect { sideEffect ->
      when (sideEffect) {
        is MlKitSideEffect.NavigateTo -> navigator.navigate(sideEffect.navKey)
        is MlKitSideEffect.NavigateUp -> navigator.navigateUp()
      }
    }
  }

  MlKitScreen(
    state = state,
    onIntent = viewModel::onIntent,
  )
}

@Composable
private fun MlKitScreen(
  state: MlKitState,
  onIntent: (MlKitIntent) -> Unit,
) {
  ApplicationTheme {
    val scrollState = rememberScrollState()

    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .statusBarsPadding()
        .verticalScroll(scrollState)
        .padding(10.dp),
      verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
      Header(
        title = when (state.api) {
          MlKitApi.Vision -> stringResource(R.string.vision_api)
          MlKitApi.NaturalLanguage -> stringResource(R.string.natural_language_api)
        },
        onBackClick = {
          onIntent(MlKitIntent.NavigateUp)
        },
      )

      val onComponentClick: (Component) -> Unit = { component ->
        onIntent(MlKitIntent.OnComponentClicked(component))
      }

      ComponentColumn(
        title = null,
        components = state.components,
        onComponentClick = onComponentClick,
      )
    }
  }
}
