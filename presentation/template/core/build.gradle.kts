plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.chandroidx.template.core"
}

dependencies {
  implementation(projects.presentation)
  implementation(libs.kotlinx.serialization)
}
