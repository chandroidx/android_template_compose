

plugins {
  alias(libs.plugins.chandroidx.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.chandroidx.mlkit"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.mlkit.api)
  implementation(projects.presentation.mlkitBarcodescanning)
  implementation(projects.presentation.mlkitFacedetection)
  implementation(projects.presentation.mlkitFacemeshdetection)
  implementation(projects.presentation.mlkitTextrecognition)
  implementation(projects.presentation.mlkitImagelabeling)
  implementation(projects.presentation.mlkitObjectdetectionandtracking)
  implementation(projects.presentation.mlkitDigitalinkrecognition)
  implementation(projects.presentation.mlkitPosedetection)
  implementation(projects.presentation.mlkitSelfiesegmentation)

  implementation(libs.kotlinx.serialization)
}
