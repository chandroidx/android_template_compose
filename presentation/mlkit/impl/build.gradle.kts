plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.chandroidx.mlkit"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.mlkit.api)

  implementation(libs.kotlinx.serialization)
  implementation(libs.bundles.mlkit)
  implementation(libs.cameraX.mlkit.vision)
  implementation(libs.accompanist.permissions)
}
