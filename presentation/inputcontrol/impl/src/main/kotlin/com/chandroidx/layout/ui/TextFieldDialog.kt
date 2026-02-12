package com.chandroidx.layout.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.chandroidx.core.ui.PageDescriptionWrapper
import com.chandroidx.core.ui.PageIndicator

@Composable
internal fun TextFieldDialog() {
  Column(
    modifier = Modifier
      .size(200.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    val pagerState = rememberPagerState { 2 }
    HorizontalPager(
      modifier = Modifier
        .padding(5.dp)
        .weight(1f),
      state = pagerState,
    ) { page ->
      when (page) {
        TEXT_FIELD -> PageDescriptionWrapper(
          description = "TextField",
          modifier = Modifier.fillMaxSize(),
        ) {
          val state = rememberTextFieldState()
          TextField(
            state = state,
            colors = TextFieldDefaults.colors(
              focusedTextColor = MaterialTheme.colorScheme.primary,
              unfocusedTextColor = MaterialTheme.colorScheme.primary,
              focusedContainerColor = MaterialTheme.colorScheme.background,
              unfocusedContainerColor = MaterialTheme.colorScheme.background,
            ),
            textStyle = TextStyle(
              color = MaterialTheme.colorScheme.primary,
            ),
          )
        }

        OUTLINED_TEXT_FIELD -> PageDescriptionWrapper(
          description = "OutlinedTextField",
          modifier = Modifier.fillMaxSize(),
        ) {
          val state = rememberTextFieldState()

          OutlinedTextField(
            state = state,
            colors = TextFieldDefaults.colors(
              focusedTextColor = MaterialTheme.colorScheme.primary,
              unfocusedTextColor = MaterialTheme.colorScheme.primary,
              focusedContainerColor = MaterialTheme.colorScheme.background,
              unfocusedContainerColor = MaterialTheme.colorScheme.background,
            ),
            textStyle = TextStyle(
              color = MaterialTheme.colorScheme.primary,
            ),
          )
        }
      }
    }

    PageIndicator(pagerState = pagerState)
  }
}

private const val TEXT_FIELD = 0
private const val OUTLINED_TEXT_FIELD = 1
