plugins {
  alias(libs.plugins.chandroidx.presentation)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.chandroidx.scheme"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.home.api)

  implementation(libs.splashScreen)
  implementation(libs.kotlinx.serialization)
  implementation(libs.androidx.compose.navigation3.viewModel)
}
