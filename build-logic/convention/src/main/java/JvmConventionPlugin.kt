import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

class JvmConventionPlugin : JvmConvention

internal interface JvmConvention : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("org.jetbrains.kotlin.jvm")
      }

      kotlinExtension.jvmToolchain(17)
    }
  }
}
