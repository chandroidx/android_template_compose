plugins {
  alias(libs.plugins.chandroidx.jvm)
  alias(libs.plugins.kotlin.serialization)
}

dependencies {
  implementation(libs.kotlinx.coroutine.core)
  implementation(libs.kotlinx.serialization)
}
