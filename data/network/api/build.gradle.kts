plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.jvm)
  alias(libs.plugins.kotlinx.serialization)
}

dependencies {
  implementation(projects.domain)

  implementation(libs.kotlinx.serialization)
  implementation(libs.kotlinx.coroutine.core)
  implementation(libs.sandwich)
  implementation(libs.ktor.logging)
}
