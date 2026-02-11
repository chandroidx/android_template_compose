package com.deepfine.naver.di

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.strategy.ComponentDialogSceneStrategy
import com.deepfine.naver.NaverNavKey
import com.deepfine.naver.SpeechRecognitionNavKey
import com.deepfine.naver.ui.NaverScreen
import com.deepfine.naver.ui.SpeechRecognitionDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object NaverModule {
  @IntoSet
  @Provides
  fun provideNaverEntryBuilder(): EntryProviderScope<NavKey>.() -> Unit = {
    entry<NaverNavKey> {
      NaverScreen(viewModel = hiltViewModel())
    }

    entry<SpeechRecognitionNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      SpeechRecognitionDialog()
    }
  }
}
