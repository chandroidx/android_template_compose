import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType

class KotestConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

      tasks.withType<Test> {
        useJUnitPlatform()
      }

      dependencies {
        add("testImplementation", libs.findBundle("test-kotest").get())
        add("testImplementation", libs.findBundle("test-coroutines").get())
        add("testImplementation", libs.findLibrary("mockk").get())
      }
    }
  }
}
