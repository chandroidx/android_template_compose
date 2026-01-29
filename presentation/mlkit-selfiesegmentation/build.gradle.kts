

plugins {
  alias(libs.plugins.chandroidx.presentation)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.chandroidx.mlkit.selfiesegmentation"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.mlkitCore)
  implementation(libs.cameraX.mlkit.vision)
  implementation(libs.mlkit.selfieSegmentation)
}
