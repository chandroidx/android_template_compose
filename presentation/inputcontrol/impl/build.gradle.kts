plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.chandroidx.inputcontrol"
}

dependencies {
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.inputcontrol.api)
  implementation(libs.kotlinx.serialization)
}
