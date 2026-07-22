plugins {
  alias(libs.plugins.spotless) apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.hilt) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.kotlin.jvm) apply false
  alias(libs.plugins.kotlinx.serialization) apply false
  alias(libs.plugins.navgraph) apply false
}

subprojects {
  plugins.withId(rootProject.libs.plugins.spotless.get().pluginId) {
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
      kotlin {
        target("src/**/*.kt", "src/**/*.kts")
        targetExclude("build/**", "**/build/**", "**/generated/**")
        ktlint()
          .customRuleSets(
            listOf(libs.spotless.composeRuleset.get().toString()),
          )
        trimTrailingWhitespace()
        endWithNewline()
      }
      format("kts") {
        target("*.gradle.kts")
        targetExclude("build/**", "**/build/**")
        trimTrailingWhitespace()
        endWithNewline()
      }
    }
  }
}

abstract class InstallGitHookTask : DefaultTask() {
  @get:InputFile
  abstract val hookSource: RegularFileProperty

  @get:OutputFile
  abstract val hookTarget: RegularFileProperty

  @get:Input
  abstract val windows: Property<Boolean>

  init {
    outputs.upToDateWhen { false }
  }

  @TaskAction
  fun install() {
    val sourceFile = hookSource.get().asFile
    val targetFile = hookTarget.get().asFile

    logger.lifecycle("🤖Running Add Git Hook Scripts on Build")

    targetFile.parentFile.mkdirs()
    java.nio.file.Files.deleteIfExists(targetFile.toPath())
    sourceFile.copyTo(targetFile)

    if (!windows.get() && !targetFile.setExecutable(true, false)) {
      throw GradleException("Failed to make Git hook executable: $targetFile")
    }

    logger.lifecycle("✅ Git Hook Scripts added.")
  }
}

val addGitHooksOnBuild = tasks.register<InstallGitHookTask>("addGitHooksOnBuild") {
  description = "Installs the Git pre-commit hook from .scripts into .git/hooks."
  hookSource.set(layout.projectDirectory.file(".scripts/pre-commit"))
  hookTarget.set(layout.projectDirectory.file(".git/hooks/pre-commit"))
  windows.set(
    providers.systemProperty("os.name")
      .map { it.contains("windows", ignoreCase = true) },
  )
}

tasks.named("prepareKotlinBuildScriptModel") {
  dependsOn(addGitHooksOnBuild)
}

allprojects {
  val parentProject = this

  subprojects {
    val childProject = this@subprojects

    childProject.plugins.withId("base") {
      parentProject.plugins.withId("base") {
        parentProject.tasks.named("clean") {
          dependsOn(childProject.tasks.named("clean"))
        }
      }
    }
  }
}
