plugins {
  alias(libs.plugins.chandroidx.android)
  alias(libs.plugins.chandroidx.compose)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.chandroidx.navigator"
}

dependencies {
  implementation(projects.domain)

  implementation(libs.androidx.compose.navigation3.runtime)
  implementation(libs.androidx.compose.navigation3.ui)
  implementation(libs.kotlinx.serialization)
}
