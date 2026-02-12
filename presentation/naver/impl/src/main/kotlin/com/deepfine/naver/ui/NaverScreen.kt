package com.deepfine.naver.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chandroidx.core.Component
import com.chandroidx.core.ui.ComponentColumn
import com.chandroidx.core.ui.Header
import com.chandroidx.navigator.LocalNavigator
import com.chandroidx.presentation.theme.ApplicationTheme
import com.chandroidx.presentation.ui.LocalSnackbar
import com.deepfine.naver.R
import com.deepfine.naver.model.NaverIntent
import com.deepfine.naver.model.NaverSideEffect
import com.deepfine.naver.model.NaverState
import com.deepfine.naver.viewmodel.NaverViewModel

@Composable
fun NaverScreen(
  viewModel: NaverViewModel,
) {
  val state by viewModel.state.collectAsStateWithLifecycle()
  val navigator = LocalNavigator.current
  val resources = LocalResources.current
  val snackbar = LocalSnackbar.current
  val scope = rememberCoroutineScope()

  LaunchedEffect(Unit) {
    viewModel.sideEffect.collect { sideEffect ->
      when (sideEffect) {
        is NaverSideEffect.NavigateTo -> navigator.navigate(sideEffect.navKey)
        is NaverSideEffect.NavigateUp -> navigator.navigateUp()
        is NaverSideEffect.NaverApiClientIdRequested -> snackbar.show(scope, resources.getString(R.string.naver_api_client_id_requested))
      }
    }
  }

  NaverScreen(
    state = state,
    onIntent = viewModel::onIntent,
  )
}

@Composable
private fun NaverScreen(
  state: NaverState,
  onIntent: (NaverIntent) -> Unit,
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
        title = stringResource(R.string.naver),
        onBackClick = {
          onIntent(NaverIntent.NavigateUp)
        },
      )

      val onComponentClick: (Component) -> Unit = { component ->
        onIntent(NaverIntent.OnComponentClicked(component))
      }

      ComponentColumn(
        title = null,
        components = state.components,
        onComponentClick = onComponentClick,
      )
    }
  }
}

@Composable
@Preview
private fun NaverScreenPreview() {
  NaverScreen(
    state = NaverState(),
    onIntent = {},
  )
}
