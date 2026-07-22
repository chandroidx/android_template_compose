plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.android)
  alias(libs.plugins.deepfine.hilt)
}

android {
  namespace = "com.chandroidx.network"
}

dependencies {
  compileOnly(projects.buildconfigStub)
  implementation(projects.data.network.api)

  implementation(libs.bundles.ktor)
}
