

plugins {
  alias(libs.plugins.chandroidx.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android  {
  namespace = "com.chandroidx.scheme"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.template.api)
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.mlkit.api)

  implementation(libs.splashScreen)
  implementation(libs.kotlinx.serialization)
  implementation(libs.androidx.compose.navigation3.viewModel)
}
