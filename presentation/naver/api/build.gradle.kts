plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.jvm)
  alias(libs.plugins.kotlinx.serialization)
}

dependencies {
  implementation(libs.kotlinx.serialization)
  implementation(libs.androidx.compose.navigation3.runtime)
}
