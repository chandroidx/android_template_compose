package com.chandroidx.core.strategy

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.OverlayScene
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import com.chandroidx.core.ComponentDialog

private class ComponentDialogScene<T : Any>(
  override val key: Any,
  override val previousEntries: List<NavEntry<T>>,
  override val overlaidEntries: List<NavEntry<T>>,
  private val dialogProperties: DialogProperties,
  private val entry: NavEntry<T>,
  private val onBack: () -> Unit,
) : OverlayScene<T> {
  override val entries: List<NavEntry<T>> = listOf(entry)

  override val content: @Composable (() -> Unit) = {
    ComponentDialog(
      onDismissRequest = onBack,
      properties = dialogProperties,
    ) {
      entry.Content()
    }
  }
}

class ComponentDialogSceneStrategy<T : Any> : SceneStrategy<T> {
  override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
    val lastEntry = entries.lastOrNull()
    val dialogProperties = lastEntry?.metadata?.get(COMPONENT_DIALOG_KEY) as? DialogProperties
    return dialogProperties?.let { properties ->
      ComponentDialogScene(
        key = lastEntry.contentKey,
        previousEntries = entries.dropLast(1),
        overlaidEntries = entries.dropLast(1),
        entry = lastEntry,
        dialogProperties = properties,
        onBack = onBack,
      )
    }
  }

  companion object {
    fun componentDialog(
      properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    ): Map<String, Any> =
      mapOf(COMPONENT_DIALOG_KEY to properties)

    internal const val COMPONENT_DIALOG_KEY = "bottomSheet"
  }
}
