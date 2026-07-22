package com.chandroidx.navigator

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

val LocalNavigator = compositionLocalOf<Navigator> {
  error("No Navigator found!")
}

interface Navigator {
  fun navigate(navKey: NavKey)

  fun navigateAsTop(navKey: NavKey, popUpTo: NavKey? = null, inclusive: Boolean = false)

  fun navigateUp()

  companion object {
    fun from(backStack: NavBackStack<NavKey>): Navigator = NavigatorImpl(backStack)
  }
}

private class NavigatorImpl(
  private val backStack: NavBackStack<NavKey>,
) : Navigator {
  override fun navigate(navKey: NavKey) {
    backStack.add(navKey)
  }

  override fun navigateAsTop(navKey: NavKey, popUpTo: NavKey?, inclusive: Boolean) {
    if (popUpTo == null) {
      backStack.clear()
    } else {
      val index = backStack.lastIndexOf(popUpTo)
      if (index != -1) {
        val removeIndex = if (inclusive) index else index + 1
        while (backStack.size > removeIndex) {
          backStack.removeAt(backStack.size - 1)
        }
      }
    }

    if (backStack.lastOrNull() != navKey) {
      backStack.add(navKey)
    }
  }

  override fun navigateUp() {
    if (backStack.size > 1) backStack.removeLastOrNull()
  }
}
