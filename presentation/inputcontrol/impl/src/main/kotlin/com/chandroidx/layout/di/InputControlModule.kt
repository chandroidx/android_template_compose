package com.chandroidx.layout.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.strategy.ComponentDialogSceneStrategy
import com.chandroidx.inputcontrol.InputControlNavKey
import com.chandroidx.layout.ui.ButtonDialog
import com.chandroidx.layout.ui.CheckboxDialog
import com.chandroidx.layout.ui.RadioButtonDialog
import com.chandroidx.layout.ui.SwitchDialog
import com.chandroidx.layout.ui.TextFieldDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object InputControlModule {
  @IntoSet
  @Provides
  fun provideInputControlEntryBuilder(): EntryProviderScope<NavKey>.() -> Unit = {
    entry<InputControlNavKey.Button>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      ButtonDialog()
    }

    entry<InputControlNavKey.Checkbox>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      CheckboxDialog()
    }

    entry<InputControlNavKey.RadioButton>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      RadioButtonDialog()
    }

    entry<InputControlNavKey.Switch>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      SwitchDialog()
    }

    entry<InputControlNavKey.TextField>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      TextFieldDialog()
    }
  }
}
