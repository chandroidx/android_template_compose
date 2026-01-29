package com.chandroidx.mlkit

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class MlKitNavKey(val api: MlKitApi) : NavKey

@Serializable
data object BarcodeScanningNavKey : NavKey

@Serializable
data object DigitalInkRecognitionNavKey : NavKey

@Serializable
data object FaceDetectionNavKey : NavKey

@Serializable
data object FaceMeshDetectionNavKey : NavKey

@Serializable
data object ImageLabelingNavKey : NavKey

@Serializable
data object ObjectDetectionAndTrackingNavKey : NavKey

@Serializable
data object PoseDetectionNavKey : NavKey

@Serializable
data object SelfieSegmentationNavKey : NavKey

@Serializable
data object TextRecognitionNavKey : NavKey
