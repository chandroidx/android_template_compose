package com.chandroidx.mlkit

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface MlKitNavKey : NavKey {
  @Serializable
  data class Screen(val api: MlKitApi) : MlKitNavKey

  @Serializable
  data object BarcodeScanning : MlKitNavKey

  @Serializable
  data object DigitalInkRecognition : MlKitNavKey

  @Serializable
  data object FaceDetection : MlKitNavKey

  @Serializable
  data object FaceMeshDetection : MlKitNavKey

  @Serializable
  data object ImageLabeling : MlKitNavKey

  @Serializable
  data object ObjectDetectionAndTracking : MlKitNavKey

  @Serializable
  data object PoseDetection : MlKitNavKey

  @Serializable
  data object SelfieSegmentation : MlKitNavKey

  @Serializable
  data object TextRecognition : MlKitNavKey
}
