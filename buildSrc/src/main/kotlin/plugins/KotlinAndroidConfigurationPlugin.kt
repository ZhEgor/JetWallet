package plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register

class KotlinAndroidConfigurationPlugin : Plugin<Project> { // TODO: Finalize

    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                compileSdk = Configs.compileSdk

                defaultConfig {
                    minSdk = Configs.minSdk
                }

            }
        }
//        target.tasks.register<HelloTask>("helloEgor")
    }
}

fun CommonExtension<*, *, *, *>.configureSdk() {
    compileSdk = Configs.compileSdk
}

abstract class HelloTask : DefaultTask() {

    @TaskAction
    fun taskAction() {
        println("Hello from " + project.parent?.name)
    }
}