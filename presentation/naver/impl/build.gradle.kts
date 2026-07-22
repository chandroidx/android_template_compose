plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.deepfine.naver"
}

dependencies {
  compileOnly(projects.buildconfigStub)
  implementation(projects.presentation)
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.naver.api)

  implementation(libs.kotlinx.serialization)
  implementation(libs.naver.speech.client)
  implementation(libs.accompanist.permissions)
}
