package com.chandroidx.home.model

sealed interface HomeIntent {
  data object OnSecondScreenButtonClicked : HomeIntent
}
