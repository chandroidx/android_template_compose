package com.chandroidx.inputcontrol

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface InputControlNavKey : NavKey {
  @Serializable
  data object Button : InputControlNavKey

  @Serializable
  data object Checkbox : InputControlNavKey

  @Serializable
  data object RadioButton : InputControlNavKey

  @Serializable
  data object Switch : InputControlNavKey

  @Serializable
  data object TextField : InputControlNavKey
}
