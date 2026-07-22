import java.util.Properties
import kotlin.apply

plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.deepfine.android)
}

val localProperties = Properties().apply {
  val localFile: File = rootProject.file("local.properties")
  if (localFile.exists()) {
    load(localFile.inputStream())
  }
}

android {
  namespace = "com.chandroidx.buildconfig"

  compileSdk = libs.versions.compileSdk.get().toInt()

  buildFeatures.buildConfig = true

  defaultConfig {
    buildConfigField("String", "VERSION_NAME", "String.valueOf(\"${libs.versions.versionName.get()}\")")
    localProperties["NAVER_API_CLIENT_ID"]?.let {
      buildConfigField("String", "NAVER_API_CLIENT_ID", "\"${it}\"")
    } ?: buildConfigField("String", "NAVER_API_CLIENT_ID", "\"\"")
  }

  flavorDimensions.add("api")

  productFlavors {
    // 개발계
    create("dev") {
      buildConfigField("String", "API_URL", "\"${project.property("api.url").toString()}\"")
      buildConfigField("String", "FLAVOR", "\"dev\"")
    }

    create("prod") {
      buildConfigField("String", "API_URL", "\"${project.property("prod.api.url").toString()}\"")
      buildConfigField("String", "FLAVOR", "\"prod\"")
    }
  }
}
