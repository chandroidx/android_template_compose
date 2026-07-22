plugins {
  alias(libs.plugins.spotless)
  alias(libs.plugins.android.application)
  alias(libs.plugins.hilt)
  alias(libs.plugins.ksp)
  alias(libs.plugins.navgraph)
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

ksp {
  arg("navgraph.annotatedOnly", "true")
}


dependencies {
  implementation(projects.buildconfig)
  implementation(projects.data)
  implementation(projects.data.network.impl)
  implementation(projects.presentation.scheme)
  implementation(projects.presentation.home.impl)

  implementation(libs.androidx.core.ktx)
  implementation(libs.splashScreen)
  implementation(libs.hilt)
  ksp(libs.hilt.compiler)
}
