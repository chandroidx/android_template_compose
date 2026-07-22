

plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.chandroidx.template"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.template.api)
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.layout.api)
  implementation(projects.presentation.inputcontrol.api)
  implementation(projects.presentation.textandtypography.api)
  implementation(projects.presentation.mlkit.api)
  implementation(projects.presentation.naver.api)

  implementation(libs.kotlinx.serialization)
}
