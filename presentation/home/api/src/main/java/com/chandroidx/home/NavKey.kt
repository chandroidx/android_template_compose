package com.chandroidx.home

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
object HomeNavKey : NavKey

@Serializable
data class SecondNavKey(val value: Int) : NavKey {
  companion object {
    const val RESULT_KEY = "key"
  }
}

@Serializable
object DialogNavKey : NavKey

@Serializable
object BottomSheetNavKey : NavKey
