package com.chandroidx.home.di

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.home.HomeNavKey
import com.chandroidx.home.SecondNavKey
import com.chandroidx.home.ui.HomeScreen
import com.chandroidx.home.ui.SecondScreen
import com.chandroidx.home.viewmodel.SecondViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeModule {
  @IntoSet
  @Provides
  fun provideHomeEntryBuilder(): EntryProviderScope<NavKey>.() -> Unit = {
    entry<HomeNavKey> {
      HomeScreen(viewModel = hiltViewModel())
    }

    entry<SecondNavKey> { key: SecondNavKey ->
      SecondScreen(
        viewModel = hiltViewModel<SecondViewModel, SecondViewModel.Factory> { factory ->
          factory.create(key.value)
        },
      )
    }
  }
}
