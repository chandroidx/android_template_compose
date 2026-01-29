package com.chandroidx.mlkit.model

import androidx.compose.runtime.Stable
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component
import com.chandroidx.mlkit.MlKitApi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class MlKitState(
  val api: MlKitApi,
  val components: ImmutableList<Component> = persistentListOf(),
)

sealed interface MlKitIntent {
  data class OnComponentClicked(val component: Component) : MlKitIntent
  data object NavigateUp : MlKitIntent
}

sealed interface MlKitSideEffect {
  data class NavigateTo(val navKey: NavKey) : MlKitSideEffect
  data object NavigateUp : MlKitSideEffect
}
