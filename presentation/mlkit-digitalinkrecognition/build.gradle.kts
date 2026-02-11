

plugins {
  alias(libs.plugins.chandroidx.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.chandroidx.mlkit.digitalinkrecognition"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.mlkitCore)
  implementation(libs.mlkit.digitalInkRecognition)
}
