package com.chandroidx.mlkit.di

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.strategy.ComponentDialogSceneStrategy
import com.chandroidx.mlkit.BarcodeScanningNavKey
import com.chandroidx.mlkit.DigitalInkRecognitionNavKey
import com.chandroidx.mlkit.FaceDetectionNavKey
import com.chandroidx.mlkit.FaceMeshDetectionNavKey
import com.chandroidx.mlkit.ImageLabelingNavKey
import com.chandroidx.mlkit.MlKitNavKey
import com.chandroidx.mlkit.ObjectDetectionAndTrackingNavKey
import com.chandroidx.mlkit.PoseDetectionNavKey
import com.chandroidx.mlkit.SelfieSegmentationNavKey
import com.chandroidx.mlkit.TextRecognitionNavKey
import com.chandroidx.mlkit.ui.BarcodeScanning
import com.chandroidx.mlkit.ui.DigitalInkRecognition
import com.chandroidx.mlkit.ui.FaceDetection
import com.chandroidx.mlkit.ui.FaceMeshDetection
import com.chandroidx.mlkit.ui.ImageLabeling
import com.chandroidx.mlkit.ui.MlKitScreen
import com.chandroidx.mlkit.ui.ObjectDetection
import com.chandroidx.mlkit.ui.PoseDetection
import com.chandroidx.mlkit.ui.SelfieSegmentation
import com.chandroidx.mlkit.ui.TextRecognition
import com.chandroidx.mlkit.viewmodel.MlKitViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object MlKitModule {
  @IntoSet
  @Provides
  fun provideMlKitEntryBuilder(): EntryProviderScope<NavKey>.() -> Unit = {
    entry<MlKitNavKey> { key ->
      MlKitScreen(
        viewModel = hiltViewModel<MlKitViewModel, MlKitViewModel.Factory> { factory ->
          factory.create(key.api)
        },
      )
    }

    entry<BarcodeScanningNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      BarcodeScanning()
    }

    entry<DigitalInkRecognitionNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      DigitalInkRecognition()
    }

    entry<FaceDetectionNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      FaceDetection()
    }

    entry<FaceMeshDetectionNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      FaceMeshDetection()
    }

    entry<ImageLabelingNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      ImageLabeling()
    }

    entry<ObjectDetectionAndTrackingNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      ObjectDetection()
    }

    entry<PoseDetectionNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      PoseDetection()
    }

    entry<SelfieSegmentationNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      SelfieSegmentation()
    }

    entry<TextRecognitionNavKey>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      TextRecognition()
    }
  }
}
