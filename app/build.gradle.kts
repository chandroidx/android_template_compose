plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.hilt)
  alias(libs.plugins.ksp)
}

android {
  namespace = libs.versions.applicationId.get()
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    applicationId = libs.versions.applicationId.get()
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = libs.versions.versionCode.get().toInt()
    versionName = libs.versions.versionName.get()
  }

  buildFeatures {
    resValues = true
  }

  flavorDimensions.add("api")

  productFlavors {
    create("dev") {
      resValue("string", "app_name", libs.versions.applicationName.get())
      resValue("string", "applicationId", libs.versions.applicationId.get() + applicationIdSuffix)
    }

    create("prod") {
      resValue("string", "app_name", libs.versions.applicationName.get())
      resValue("string", "applicationId", libs.versions.applicationId.get())
    }
  }

  buildTypes {
    debug {
      isDebuggable = true
      isMinifyEnabled = false
      isShrinkResources = false
      splits.abi.isEnable = false
    }

    release {
      isDebuggable = false
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//            signingConfig = signingConfigs.getByName("release")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
}

dependencies {
  implementation(projects.buildconfig)
  implementation(projects.data.impl)
  implementation(projects.data.network.impl)

  implementation(projects.presentation.scheme)
  implementation(projects.presentation.naver.impl)
  implementation(projects.presentation.template.impl)
  implementation(projects.presentation.layout.impl)
  implementation(projects.presentation.inputcontrol.impl)
  implementation(projects.presentation.textandtypography.impl)
  implementation(projects.presentation.mlkit.impl)

  implementation(libs.androidx.core.ktx)
  implementation(libs.splashScreen)
  implementation(libs.hilt)
  ksp(libs.hilt.compiler)
}
