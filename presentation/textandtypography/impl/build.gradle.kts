

plugins {
  alias(libs.plugins.chandroidx.presentation)
  alias(libs.plugins.kotlinx.serialization)
}

android {
  namespace = "com.chandroidx.textandtypography"
}

dependencies {
  implementation(projects.presentation.template.core)
  implementation(projects.presentation.textandtypography.api)
  implementation(libs.kotlinx.serialization)
}
