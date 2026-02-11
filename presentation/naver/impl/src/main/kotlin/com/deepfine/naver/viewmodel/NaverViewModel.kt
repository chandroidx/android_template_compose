package com.deepfine.naver.viewmodel

import androidx.lifecycle.ViewModel
import com.chandroidx.core.Component
import com.deepfine.naver.model.NaverApi
import com.deepfine.naver.model.NaverIntent
import com.deepfine.naver.model.NaverSideEffect
import com.deepfine.naver.model.NaverState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class NaverViewModel @Inject constructor() : ViewModel() {
  private val _state = MutableStateFlow(
    NaverState(
      components = NaverApi.all().toPersistentList(),
    ),
  )
  val state: StateFlow<NaverState>
    get() = _state.asStateFlow()

  private val _sideEffect = Channel<NaverSideEffect>(capacity = Channel.BUFFERED)
  val sideEffect: Flow<NaverSideEffect>
    get() = _sideEffect.receiveAsFlow()

  fun onIntent(intent: NaverIntent) {
    when (intent) {
      is NaverIntent.OnComponentClicked -> onComponentClicked(intent.component)
      is NaverIntent.NavigateUp -> navigateUp()
    }
  }

  private fun onComponentClicked(component: Component) {
    component.navKey?.let { navKey ->
      _sideEffect.trySend(NaverSideEffect.NavigateTo(navKey))
    }
  }

  private fun navigateUp() {
    _sideEffect.trySend(NaverSideEffect.NavigateUp)
  }
}
