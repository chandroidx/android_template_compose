

plugins {
  alias(libs.plugins.chandroidx.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.chandroidx.template.core"
}

dependencies {
  implementation(projects.presentation)
  implementation(libs.kotlinx.serialization)
}
