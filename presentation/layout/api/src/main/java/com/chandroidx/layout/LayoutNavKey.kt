package com.chandroidx.layout

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface LayoutNavKey : NavKey {
  @Serializable
  data object Box : LayoutNavKey

  @Serializable
  data object Column : LayoutNavKey

  @Serializable
  data object Row : LayoutNavKey
}
