package com.chandroidx.home

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HomeNavKey : NavKey

@Serializable
data class SecondNavKey(val value: Int) : NavKey {
  data class Result(val value: Int)
}
