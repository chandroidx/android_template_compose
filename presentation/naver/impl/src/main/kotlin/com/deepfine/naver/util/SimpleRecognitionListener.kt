package com.deepfine.naver.util

import com.naver.speech.clientapi.SpeechConfig
import com.naver.speech.clientapi.SpeechRecognitionListener
import com.naver.speech.clientapi.SpeechRecognitionResult

open class SimpleRecognitionListener : SpeechRecognitionListener {
  override fun onInactive() {
  }

  override fun onReady() {
  }

  override fun onRecord(speech: ShortArray?) {
  }

  override fun onPartialResult(partialResult: String?) {
  }

  override fun onEndPointDetected() {
  }

  override fun onResult(finalResult: SpeechRecognitionResult?) {
  }

  override fun onError(errorCode: Int) {
  }

  override fun onEndPointDetectTypeSelected(epdType: SpeechConfig.EndPointDetectType?) {
  }
}
