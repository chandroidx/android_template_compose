package com.chandroidx.mlkit.model

import androidx.navigation3.runtime.NavKey
import com.chandroidx.core.Component
import com.chandroidx.mlkit.MlKitNavKey
import com.chandroidx.mlkit.R

sealed interface MlKit {
  sealed interface Vision : Component {
    data object BarcodeScanning : Vision {
      override val navKey: NavKey = MlKitNavKey.BarcodeScanning
      override val iconResId: Int = R.drawable.ico_barcode
      override val textResId: Int = R.string.barcode_scanning
      override val descriptionResId: Int? = null
    }

    data object FaceDetection : Vision {
      override val navKey: NavKey = MlKitNavKey.FaceDetection
      override val iconResId: Int = R.drawable.ico_face_detection
      override val textResId: Int = R.string.face_detection
      override val descriptionResId: Int? = null
    }

    data object FaceMeshDetection : Vision {
      override val navKey: NavKey = MlKitNavKey.FaceMeshDetection
      override val iconResId: Int = R.drawable.ico_face_mesh_detection
      override val textResId: Int = R.string.face_mesh_detection
      override val descriptionResId: Int? = null
    }

    data object TextRecognition : Vision {
      override val navKey: NavKey = MlKitNavKey.TextRecognition
      override val iconResId: Int = R.drawable.ico_text_recognition
      override val textResId: Int = R.string.text_recognition
      override val descriptionResId: Int? = null
    }

    data object ImageLabeling : Vision {
      override val navKey: NavKey = MlKitNavKey.ImageLabeling
      override val iconResId: Int = R.drawable.ico_image_labeling
      override val textResId: Int = R.string.image_labeling
      override val descriptionResId: Int? = null
    }

    data object ObjectDetectionAndTracking : Vision {
      override val navKey: NavKey = MlKitNavKey.ObjectDetectionAndTracking
      override val iconResId: Int = R.drawable.ico_object_detection_and_tracking
      override val textResId: Int = R.string.object_detection_and_tracking
      override val descriptionResId: Int? = null
    }

    data object DigitalInkRecognition : Vision {
      override val navKey: NavKey = MlKitNavKey.DigitalInkRecognition
      override val iconResId: Int = R.drawable.ico_digital_ink_recognition
      override val textResId: Int = R.string.digital_ink_recognition
      override val descriptionResId: Int? = null
    }

    data object PoseDetection : Vision {
      override val navKey: NavKey = MlKitNavKey.PoseDetection
      override val iconResId: Int = R.drawable.ico_pose_detection
      override val textResId: Int = R.string.pose_detection
      override val descriptionResId: Int? = null
    }

    data object SelfieSegmentation : Vision {
      override val navKey: NavKey = MlKitNavKey.SelfieSegmentation
      override val iconResId: Int = R.drawable.ico_selfie_segmentation
      override val textResId: Int = R.string.selfie_segmentation
      override val descriptionResId: Int? = null
    }

    data object SubjectSegmentation : Vision {
      override val navKey: NavKey? = null
      override val iconResId: Int = R.drawable.ico_subject_segmentation
      override val textResId: Int = R.string.subject_segmentation
      override val descriptionResId: Int? = null
    }

    data object DocumentScanner : Vision {
      override val navKey: NavKey? = null
      override val iconResId: Int = R.drawable.ico_document_scanner
      override val textResId: Int = R.string.document_scanner
      override val descriptionResId: Int? = null
    }

    companion object {
      fun all(): List<Component> = listOf(BarcodeScanning, FaceDetection, FaceMeshDetection, TextRecognition, ImageLabeling, ObjectDetectionAndTracking, DigitalInkRecognition, PoseDetection, SelfieSegmentation, SubjectSegmentation, DocumentScanner)
    }
  }

  sealed interface NaturalLanguage : Component {
    data object LanguageIdentification : NaturalLanguage {
      override val navKey: NavKey? = null
      override val iconResId: Int = R.drawable.ico_language_identification
      override val textResId: Int = R.string.language_identification
      override val descriptionResId: Int? = null
    }

    data object Translation : NaturalLanguage {
      override val navKey: NavKey? = null
      override val iconResId: Int = R.drawable.ico_translation
      override val textResId: Int = R.string.translation
      override val descriptionResId: Int? = null
    }

    data object SmartReply : NaturalLanguage {
      override val navKey: NavKey? = null
      override val iconResId: Int = R.drawable.ico_smart_reply
      override val textResId: Int = R.string.smart_reply
      override val descriptionResId: Int? = null
    }

    data object EntityExtraction : NaturalLanguage {
      override val navKey: NavKey? = null
      override val iconResId: Int = R.drawable.ico_entity_extraction
      override val textResId: Int = R.string.entity_extraction
      override val descriptionResId: Int? = null
    }

    companion object {
      fun all() = listOf(LanguageIdentification, Translation, SmartReply, EntityExtraction)
    }
  }

  companion object {
    fun all() = listOf(Vision, NaturalLanguage)
  }
}
