plugins {
  alias(libs.plugins.chandroidx.android)
  alias(libs.plugins.chandroidx.hilt)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.chandroidx.data"
}

dependencies {
  implementation(projects.data.api)
  implementation(projects.data.network.api)
  runtimeOnly(projects.data.network.impl)

  implementation(libs.kotlinx.serialization)
}
