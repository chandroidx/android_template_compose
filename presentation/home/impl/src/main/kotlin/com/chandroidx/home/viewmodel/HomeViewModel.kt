package com.chandroidx.home.viewmodel

import androidx.lifecycle.ViewModel
import com.chandroidx.home.model.HomeIntent
import com.chandroidx.home.model.HomeSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

  private val _sideEffect = Channel<HomeSideEffect>(Channel.BUFFERED)
  val sideEffect: Flow<HomeSideEffect>
    get() = _sideEffect.receiveAsFlow()

  internal fun onIntent(intent: HomeIntent) {
    when (intent) {
      HomeIntent.NavigateToBottomSheet -> _sideEffect.trySend(HomeSideEffect.NavigateToBottomSheet)
      HomeIntent.NavigateToDialog -> _sideEffect.trySend(HomeSideEffect.NavigateToDialog)
      HomeIntent.NavigateToSecondScreen -> _sideEffect.trySend(HomeSideEffect.NavigateToSecondScreen)
    }
  }
}
