package com.chandroidx.template.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chandroidx.core.Component
import com.chandroidx.core.ui.ComponentColumn
import com.chandroidx.navigator.LocalNavigator
import com.chandroidx.presentation.theme.ApplicationTheme
import com.chandroidx.template.model.Api
import com.chandroidx.template.model.InputControl
import com.chandroidx.template.model.Layout
import com.chandroidx.template.model.MlKit
import com.chandroidx.template.model.TemplateIntent
import com.chandroidx.template.model.TemplateSideEffect
import com.chandroidx.template.model.TemplateState
import com.chandroidx.template.model.TextAndTypography
import com.chandroidx.template.viewModel.TemplateViewModel
import kotlinx.collections.immutable.toPersistentList

@Composable
fun TemplateScreen(
  viewModel: TemplateViewModel,
) {
  val state by viewModel.state.collectAsState()

  val navigator = LocalNavigator.current

  LaunchedEffect(Unit) {
    viewModel.sideEffect.collect { sideEffect ->
      when (sideEffect) {
        is TemplateSideEffect.NavigateTo -> navigator.navigate(sideEffect.navKey)
      }
    }
  }

  TemplateScreen(
    state = state,
    onIntent = viewModel::onIntent,
  )
}

@Composable
private fun TemplateScreen(
  state: TemplateState,
  onIntent: (TemplateIntent) -> Unit,
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
      val onComponentClick: (Component) -> Unit = { component ->
        onIntent(TemplateIntent.OnComponentClicked(component))
      }

      ComponentColumn(
        title = "Layout",
        components = Layout.all().toPersistentList(),
        onComponentClick = onComponentClick,
      )

      ComponentColumn(
        title = "Input Controls",
        components = InputControl.all().toPersistentList(),
        onComponentClick = onComponentClick,
      )

      ComponentColumn(
        title = "Text & Typography",
        components = TextAndTypography.all().toPersistentList(),
        onComponentClick = onComponentClick,
      )

      ComponentColumn(
        title = "ML Kit",
        components = MlKit.all().toPersistentList(),
        onComponentClick = onComponentClick,
      )

      ComponentColumn(
        title = "APIs",
        components = Api.all().toPersistentList(),
        onComponentClick = onComponentClick,
      )

      Spacer(
        modifier = Modifier.navigationBarsPadding(),
      )
    }
  }
}

@Composable
@Preview
private fun TemplateScreenPreview() {
  ApplicationTheme {
    TemplateScreen(
      state = TemplateState,
      onIntent = {},
    )
  }
}
