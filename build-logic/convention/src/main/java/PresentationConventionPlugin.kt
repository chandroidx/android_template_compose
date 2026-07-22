import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class PresentationConventionPlugin : AndroidConvention, HiltConvention, ComposeConvention {
  override fun apply(target: Project) {
    super<AndroidConvention>.apply(target)
    super<HiltConvention>.apply(target)
    super<ComposeConvention>.apply(target)

    with(target) {
      with(pluginManager) {
        apply("com.github.skydoves.navgraph")
      }
    }


    with(target) {
      extensions.getByType<LibraryExtension>().apply {
        androidResources.enable = true
      }
      dependencies {
        add("implementation", project(":navigator"))
        add("implementation", project(":domain"))
      }
    }
  }
}
