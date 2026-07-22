plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.presentation)
}

android {
  namespace = "com.chandroidx.scheme"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.home.api)

  implementation(libs.splashScreen)
  implementation(libs.androidx.compose.navigation3.viewModel)
}
