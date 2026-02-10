plugins {
  `kotlin-dsl`
}

kotlin {
  jvmToolchain(17)
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.compose.gradlePlugin)
  compileOnly(libs.spotless.composeRuleset)
}

gradlePlugin {
  plugins {
    register("PresentationPlugin") {
      id = "com.chandroidx.presentation.plugin"
      implementationClass = "PresentationConventionPlugin"
    }

    register("JvmPlugin") {
      id = "com.chandroidx.jvm.plugin"
      implementationClass = "JvmConventionPlugin"
    }

    register("AndroidPlugin") {
      id = "com.chandroidx.android.plugin"
      implementationClass = "AndroidConventionPlugin"
    }

    register("HiltPlugin") {
      id = "com.chandroidx.hilt.plugin"
      implementationClass = "HiltConventionPlugin"
    }

    register("ComposePlugin") {
      id = "com.chandroidx.compose.plugin"
      implementationClass = "ComposeConventionPlugin"
    }
  }
}