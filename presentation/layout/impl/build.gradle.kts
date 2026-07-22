plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.chandroidx.layout"
}

dependencies {
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.layout.api)
  implementation(libs.kotlinx.serialization)
}
