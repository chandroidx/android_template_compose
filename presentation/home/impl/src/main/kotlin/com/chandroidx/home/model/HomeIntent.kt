package com.chandroidx.home.model

sealed interface HomeIntent {
  data object NavigateToSecondScreen : HomeIntent

  data object NavigateToDialog : HomeIntent

  data object NavigateToBottomSheet : HomeIntent
}
