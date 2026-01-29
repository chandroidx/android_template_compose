package com.chandroidx.template.viewModel

import androidx.lifecycle.ViewModel
import com.chandroidx.core.Component
import com.chandroidx.template.model.TemplateIntent
import com.chandroidx.template.model.TemplateSideEffect
import com.chandroidx.template.model.TemplateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor() : ViewModel() {
  private val _state = MutableStateFlow(TemplateState)
  val state: StateFlow<TemplateState>
    get() = _state.asStateFlow()

  private val _sideEffect = Channel<TemplateSideEffect>(Channel.BUFFERED)
  val sideEffect: Flow<TemplateSideEffect>
    get() = _sideEffect.receiveAsFlow()

  internal fun onIntent(intent: TemplateIntent) {
    when (intent) {
      is TemplateIntent.OnComponentClicked -> onComponentClicked(intent.component)
    }
  }

  private fun onComponentClicked(component: Component) {
    component.navKey?.let { navKey ->
      _sideEffect.trySend(TemplateSideEffect.NavigateTo(navKey))
    }
  }
}
