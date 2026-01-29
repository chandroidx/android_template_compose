package com.chandroidx.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import tech.thdev.compose.exteions.system.ui.controller.rememberSystemUiController

@Composable
fun ApplicationTheme(
  systemBarsColor: Color = Color.Transparent,
  statusBarColor: Color = systemBarsColor,
  navigationBarColor: Color = systemBarsColor,
  isSystemBarVisible: Boolean = true,
  isStatusBarVisible: Boolean = isSystemBarVisible,
  isNavigationBarVisible: Boolean = isSystemBarVisible,
  systemBarsDarkContentEnabled: Boolean = !isSystemInDarkTheme(),
  statusBarDarkContentEnabled: Boolean = systemBarsDarkContentEnabled,
  navigationBarDarkContentEnabled: Boolean = systemBarsDarkContentEnabled,
  content: @Composable () -> Unit,
) {
  val systemUIController = rememberSystemUiController()

  DisposableEffect(systemUIController) {
    systemUIController.setSystemBarsColor(systemBarsColor)
    systemUIController.setStatusBarColor(statusBarColor)
    systemUIController.setNavigationBarColor(navigationBarColor)
    systemUIController.isSystemBarsVisible = isSystemBarVisible
    systemUIController.isStatusBarVisible = isStatusBarVisible
    systemUIController.isNavigationBarVisible = isNavigationBarVisible
    systemUIController.systemBarsDarkContentEnabled = systemBarsDarkContentEnabled
    systemUIController.statusBarDarkContentEnabled = statusBarDarkContentEnabled
    systemUIController.navigationBarDarkContentEnabled = navigationBarDarkContentEnabled
    onDispose { }
  }

  MaterialTheme(
    colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme,
    typography = Typography,
    content = {
      ProvideTextStyle(defaultTextStyle) {
        content()
      }
    },
  )
}
