package com.chandroidx.textandtypography.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.strategy.ComponentDialogSceneStrategy
import com.chandroidx.textandtypography.TextAndTypographyNavKey
import com.chandroidx.textandtypography.ui.AnnotatedStringDialog
import com.chandroidx.textandtypography.ui.TextDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object TextAndTypographyModule {
  @IntoSet
  @Provides
  fun provideTextAndTypographyEntryBuilder(): EntryProviderScope<NavKey>.() -> Unit = {
    entry<TextAndTypographyNavKey.Text>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      TextDialog()
    }

    entry<TextAndTypographyNavKey.AnnotatedString>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      AnnotatedStringDialog()
    }
  }
}
