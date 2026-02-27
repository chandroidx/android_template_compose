plugins {
  alias(libs.plugins.chandroidx.android)
  alias(libs.plugins.chandroidx.hilt)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.chandroidx.data"
}

dependencies {
  implementation(projects.domain)
  implementation(projects.data.network.api)

  implementation(libs.kotlinx.serialization)
}
