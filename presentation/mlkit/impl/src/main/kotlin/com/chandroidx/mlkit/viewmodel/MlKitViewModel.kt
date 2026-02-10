package com.chandroidx.mlkit.viewmodel

import androidx.lifecycle.ViewModel
import com.chandroidx.core.Component
import com.chandroidx.mlkit.MlKitApi
import com.chandroidx.mlkit.model.MlKit
import com.chandroidx.mlkit.model.MlKitIntent
import com.chandroidx.mlkit.model.MlKitSideEffect
import com.chandroidx.mlkit.model.MlKitState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel(assistedFactory = MlKitViewModel.Factory::class)
class MlKitViewModel @AssistedInject constructor(
  @Assisted api: MlKitApi,
) : ViewModel() {
  private val _state = MutableStateFlow(
    MlKitState(
      api = api,
      components = when (api) {
        MlKitApi.Vision -> MlKit.Vision.all().toPersistentList()
        MlKitApi.NaturalLanguage -> MlKit.NaturalLanguage.all().toPersistentList()
      },
    ),
  )

  val state: StateFlow<MlKitState>
    get() = _state.asStateFlow()

  private val _sideEffect = Channel<MlKitSideEffect>(Channel.BUFFERED)
  val sideEffect: Flow<MlKitSideEffect>
    get() = _sideEffect.receiveAsFlow()

  fun onIntent(intent: MlKitIntent) {
    when (intent) {
      is MlKitIntent.OnComponentClicked -> onComponentClicked(intent.component)
      is MlKitIntent.NavigateUp -> navigateUp()
    }
  }

  private fun onComponentClicked(component: Component) {
    component.navKey?.let { navKey ->
      _sideEffect.trySend(MlKitSideEffect.NavigateTo(navKey))
    }
  }

  private fun navigateUp() {
    _sideEffect.trySend(MlKitSideEffect.NavigateUp)
  }

  @AssistedFactory
  interface Factory {
    fun create(api: MlKitApi): MlKitViewModel
  }
}
