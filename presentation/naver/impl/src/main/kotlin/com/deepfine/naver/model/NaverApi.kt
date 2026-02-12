package com.deepfine.naver.model

import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component
import com.deepfine.naver.NaverNavKey
import com.deepfine.naver.R

sealed interface NaverApi : Component {
  data object SpeechRecognition : NaverApi {
    override val navKey: NavKey = NaverNavKey.SpeechRecognition
    override val iconResId: Int = R.drawable.ico_speech_recognition
    override val textResId: Int = R.string.naver_speech_recognition
    override val descriptionResId: Int? = null
  }

  companion object {
    fun all() = listOf<NaverApi>(SpeechRecognition)
  }
}
