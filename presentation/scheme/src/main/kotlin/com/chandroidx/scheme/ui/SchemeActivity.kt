package com.chandroidx.scheme.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.scene.SinglePaneSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.chandroidx.core.strategy.ComponentDialogSceneStrategy
import com.chandroidx.navigator.LocalNavigator
import com.chandroidx.navigator.LocalResultStore
import com.chandroidx.navigator.Navigator
import com.chandroidx.navigator.rememberResultStore
import com.chandroidx.presentation.strategy.BottomSheetSceneStrategy
import com.chandroidx.presentation.ui.LocalSnackbar
import com.chandroidx.presentation.ui.Snackbar
import com.chandroidx.presentation.ui.rememberSnackbarState
import com.chandroidx.template.TemplateNavKey
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SchemeActivity : ComponentActivity() {
  @Inject
  lateinit var entryBuilders: Set<@JvmSuppressWildcards EntryProviderScope<NavKey>.() -> Unit>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    installSplashScreen()
    enableEdgeToEdge()

    setContent {
      val backStack = remember { mutableStateListOf<NavKey>(TemplateNavKey) }
      val snackbarState = rememberSnackbarState()
      val componentDialogStrategy = remember { ComponentDialogSceneStrategy<NavKey>() }
      val bottomSheetStrategy = remember { BottomSheetSceneStrategy<NavKey>() }
      val dialogStrategy = remember { DialogSceneStrategy<NavKey>() }
      val singlePaneStrategy = remember { SinglePaneSceneStrategy<NavKey>() }

      CompositionLocalProvider(
        LocalNavigator provides Navigator.from(backStack),
        LocalSnackbar provides snackbarState,
        LocalResultStore provides rememberResultStore(),
      ) {
        Box(modifier = Modifier.fillMaxSize()) {
          NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            sceneStrategies = listOf(componentDialogStrategy, bottomSheetStrategy, dialogStrategy, singlePaneStrategy),
            entryDecorators = listOf(
              rememberSaveableStateHolderNavEntryDecorator(),
              rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
              entryBuilders.forEach { builder -> this.builder() }
            },
            transitionSpec = {
              slideInHorizontally(initialOffsetX = { it }) togetherWith
                slideOutHorizontally(targetOffsetX = { -it })
            },
            popTransitionSpec = {
              slideInHorizontally(initialOffsetX = { -it }) togetherWith
                slideOutHorizontally(targetOffsetX = { it })
            },
            predictivePopTransitionSpec = {
              slideInHorizontally(initialOffsetX = { -it }) togetherWith
                slideOutHorizontally(targetOffsetX = { it })
            },
          )

          Snackbar(state = snackbarState)
        }
      }
    }
  }
}
