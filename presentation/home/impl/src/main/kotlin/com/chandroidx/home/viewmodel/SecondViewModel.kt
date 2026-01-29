package com.chandroidx.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chandroidx.home.model.SecondState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

@HiltViewModel(assistedFactory = SecondViewModel.Factory::class)
class SecondViewModel @AssistedInject constructor(
  @Assisted private val value: Int,
) : ViewModel() {

  private val _state = MutableStateFlow(SecondState(value))
  val state: StateFlow<SecondState>
    get() = _state.asStateFlow()

  init {
    viewModelScope.launch {
      while (true) {
        delay(1000L)
        _state.update {
          it.copy(value = Random.nextInt())
        }
      }
    }
  }

  @AssistedFactory
  interface Factory {
    fun create(value: Int): SecondViewModel
  }
}
