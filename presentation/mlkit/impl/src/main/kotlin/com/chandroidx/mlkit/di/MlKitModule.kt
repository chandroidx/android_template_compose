package com.chandroidx.mlkit.di

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.strategy.ComponentDialogSceneStrategy
import com.chandroidx.mlkit.MlKitNavKey
import com.chandroidx.mlkit.ui.BarcodeScanningDialog
import com.chandroidx.mlkit.ui.DigitalInkRecognitionDialog
import com.chandroidx.mlkit.ui.FaceDetectionDialog
import com.chandroidx.mlkit.ui.FaceMeshDetectionDialog
import com.chandroidx.mlkit.ui.ImageLabelingDialog
import com.chandroidx.mlkit.ui.MlKitScreen
import com.chandroidx.mlkit.ui.ObjectDetectionDialog
import com.chandroidx.mlkit.ui.PoseDetectionDialog
import com.chandroidx.mlkit.ui.SelfieSegmentationDialog
import com.chandroidx.mlkit.ui.TextRecognitionDialog
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
    entry<MlKitNavKey.Screen> { key ->
      MlKitScreen(
        viewModel = hiltViewModel<MlKitViewModel, MlKitViewModel.Factory> { factory ->
          factory.create(key.api)
        },
      )
    }

    entry<MlKitNavKey.BarcodeScanning>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      BarcodeScanningDialog()
    }

    entry<MlKitNavKey.DigitalInkRecognition>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      DigitalInkRecognitionDialog()
    }

    entry<MlKitNavKey.FaceDetection>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      FaceDetectionDialog()
    }

    entry<MlKitNavKey.FaceMeshDetection>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      FaceMeshDetectionDialog()
    }

    entry<MlKitNavKey.ImageLabeling>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      ImageLabelingDialog()
    }

    entry<MlKitNavKey.ObjectDetectionAndTracking>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      ObjectDetectionDialog()
    }

    entry<MlKitNavKey.PoseDetection>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      PoseDetectionDialog()
    }

    entry<MlKitNavKey.SelfieSegmentation>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      SelfieSegmentationDialog()
    }

    entry<MlKitNavKey.TextRecognition>(
      metadata = ComponentDialogSceneStrategy.componentDialog(),
    ) {
      TextRecognitionDialog()
    }
  }
}
