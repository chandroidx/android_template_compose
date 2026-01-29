@file:Suppress("UnstableApiUsage")
rootProject.name = "mob_android_template_compose"

pluginManagement {
  includeBuild("build-logic")

  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven(url = "https://www.jitpack.io")
  }
}

include(
  ":app",
  ":buildconfig",
  ":buildconfig-stub",
  ":data:api",
  ":data:impl",
  ":data:network:api",
  ":data:network:impl",
  ":navigator",
  ":presentation",
  ":presentation:scheme",
  ":presentation:template:api",
  ":presentation:template:impl",
  ":presentation:template:core",
  ":presentation:textandtypography:api",
  ":presentation:textandtypography:impl",
  ":presentation:inputcontrol:api",
  ":presentation:inputcontrol:impl",
  ":presentation:layout:api",
  ":presentation:layout:impl",
  ":presentation:mlkit:api",
  ":presentation:mlkit:impl",
  ":presentation:mlkit-core",
  ":presentation:mlkit-textrecognition",
  ":presentation:mlkit-barcodescanning",
  ":presentation:mlkit-facedetection",
  ":presentation:mlkit-facemeshdetection",
  ":presentation:mlkit-imagelabeling",
  ":presentation:mlkit-objectdetectionandtracking",
  ":presentation:mlkit-digitalinkrecognition",
  ":presentation:mlkit-posedetection",
  ":presentation:mlkit-selfiesegmentation"
)

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))