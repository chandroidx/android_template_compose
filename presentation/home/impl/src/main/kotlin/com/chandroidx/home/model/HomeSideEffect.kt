package com.chandroidx.home.model

interface HomeSideEffect {
  data object NavigateToSecondScreen : HomeSideEffect
  data object NavigateToDialog : HomeSideEffect
  data object NavigateToBottomSheet : HomeSideEffect
}
