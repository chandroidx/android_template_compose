plugins {
  alias(libs.plugins.chandroidx.android)
  alias(libs.plugins.chandroidx.hilt)
}

android {
  namespace = "com.chandroidx.data.network"
}

dependencies {
  compileOnly(projects.buildconfigStub)
  implementation(projects.data.network.api)

  implementation(libs.kotlinx.serialization)
  implementation(libs.bundles.ktor)
}
