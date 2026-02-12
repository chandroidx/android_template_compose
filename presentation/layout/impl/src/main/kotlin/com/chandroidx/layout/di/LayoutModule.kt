package com.chandroidx.layout.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.strategy.ComponentDialogSceneStrategy
import com.chandroidx.layout.LayoutNavKey
import com.chandroidx.layout.ui.BoxDialog
import com.chandroidx.layout.ui.ColumnDialog
import com.chandroidx.layout.ui.RowDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object LayoutModule {
  @IntoSet
  @Provides
  fun provideLayoutEntryBuilder(): EntryProviderScope<NavKey>.() -> Unit = {
    entry<LayoutNavKey.Box>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      BoxDialog()
    }

    entry<LayoutNavKey.Column>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      ColumnDialog()
    }

    entry<LayoutNavKey.Row>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      RowDialog()
    }
  }
}
