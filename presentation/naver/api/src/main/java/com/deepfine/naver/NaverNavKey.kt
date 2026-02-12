package com.deepfine.naver

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NaverNavKey : NavKey {
  @Serializable
  data object Screen : NaverNavKey

  @Serializable
  object SpeechRecognition : NaverNavKey
}
