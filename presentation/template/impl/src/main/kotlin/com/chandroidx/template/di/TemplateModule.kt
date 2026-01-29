package com.chandroidx.template.di

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.template.TemplateNavKey
import com.chandroidx.template.ui.TemplateScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object TemplateModule {
  @IntoSet
  @Provides
  fun provideTemplateEntryBuilder(): EntryProviderScope<NavKey>.() -> Unit = {
    entry<TemplateNavKey> {
      TemplateScreen(viewModel = hiltViewModel())
    }
  }
}
