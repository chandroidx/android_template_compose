@file:Suppress("UnstableApiUsage")
rootProject.name = "android_template_compose"

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
  ":presentation:home:api",
  ":presentation:home:impl"
)

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))