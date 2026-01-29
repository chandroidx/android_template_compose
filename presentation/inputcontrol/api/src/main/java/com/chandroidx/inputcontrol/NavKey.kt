package com.chandroidx.inputcontrol

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object ButtonNavKey : NavKey

@Serializable
data object CheckBoxNavKey : NavKey

@Serializable
data object RadioButtonNavKey : NavKey

@Serializable
data object SwitchNavKey : NavKey

@Serializable
data object TextFieldNavKey : NavKey
