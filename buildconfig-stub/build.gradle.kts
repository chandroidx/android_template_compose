plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.android)
}

android {
  namespace = "com.chandroidx.buildconfig"

  compileSdk = libs.versions.compileSdk.get().toInt()

  buildFeatures.buildConfig = true

  defaultConfig {
    buildConfigField("String", "VERSION_NAME", "String.valueOf(\"0\")")
    buildConfigField("String", "FLAVOR", "String.valueOf(\"0\")")
    buildConfigField("String", "API_URL", "String.valueOf(\"\")")
    buildConfigField("String", "NAVER_API_CLIENT_ID", "String.valueOf(\"\")")
  }
}
