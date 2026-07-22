plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.android)
  alias(libs.plugins.deepfine.hilt)
}

android {
  namespace = "com.chandroidx.data"
}

dependencies {
  implementation(projects.domain)
  implementation(projects.data.network.api)
}
