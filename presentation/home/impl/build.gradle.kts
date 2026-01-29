plugins {
  alias(libs.plugins.chandroidx.presentation)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.chandroidx.home"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.home.api)

  implementation(libs.kotlinx.serialization)
}
