plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.presentation)
}

android {
  namespace = "com.chandroidx.home"
}

dependencies {
  implementation(projects.presentation)
  implementation(projects.presentation.home.api)
}
