plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.jvm)
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.runtime)

  implementation(libs.kotlinx.coroutine.core)
}
