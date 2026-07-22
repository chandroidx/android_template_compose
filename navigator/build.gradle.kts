plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.android)
  alias(libs.plugins.deepfine.compose)
}

android {
  namespace = "com.chandroidx.navigator"
}

dependencies {
  implementation(projects.domain)

  implementation(libs.androidx.compose.navigation3.runtime)
  implementation(libs.androidx.compose.navigation3.ui)
}
