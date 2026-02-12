package com.deepfine.naver.model

import androidx.compose.runtime.Stable
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class NaverState(
  val components: ImmutableList<Component> = persistentListOf(),
)

sealed interface NaverIntent {
  data object NavigateUp : NaverIntent

  data class OnComponentClicked(val component: Component) : NaverIntent
}

sealed interface NaverSideEffect {
  data class NavigateTo(val navKey: NavKey) : NaverSideEffect

  data object NavigateUp : NaverSideEffect

  data object NaverApiClientIdRequested : NaverSideEffect
}
