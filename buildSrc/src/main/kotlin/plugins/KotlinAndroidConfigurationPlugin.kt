package plugins

import Configs
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileSystemOperations
import org.gradle.api.file.FileType
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.gradle.api.tasks.options.Option
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.submit
import org.gradle.work.ChangeType
import org.gradle.work.Incremental
import org.gradle.work.InputChanges
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import org.gradle.workers.WorkerExecutor
import javax.inject.Inject

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

abstract class IncrementalReverseTask : DefaultTask() {
    @get:Incremental
    @get:PathSensitive(PathSensitivity.NAME_ONLY)
    @get:InputDirectory
    abstract val inputDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Input
    abstract val inputProperty: Property<String>

    @TaskAction
    fun execute(inputChanges: InputChanges) {
        println(
            if (inputChanges.isIncremental) "Executing incrementally"
            else "Executing non-incrementally"
        )

        inputChanges.getFileChanges(inputDir).forEach { change ->
            if (change.fileType == FileType.DIRECTORY) return@forEach

            println("${change.changeType}: ${change.normalizedPath}")
            val targetFile = outputDir.file(change.normalizedPath).get().asFile
            if (change.changeType == ChangeType.REMOVED) {
                targetFile.delete()
            } else {
                targetFile.writeText(change.file.readText().reversed())
            }
        }
    }
}

abstract class UrlVerify : DefaultTask() {
    @get:Input
    @set:Option(option = "url", description = "Configures the URL to be verified.")
    var url: String? = null

    @TaskAction
    fun verify() {
        logger.quiet("Verifying URL '{}'", url)

        // verify URL by making a HTTP call
    }
}

// The parameters for a single unit of work
interface ReverseParameters : WorkParameters {
    val fileToReverse : RegularFileProperty
    val destinationDir : DirectoryProperty
}

abstract class ReverseFile @Inject constructor(val fileSystemOperations: FileSystemOperations) : WorkAction<ReverseParameters> {
    override fun execute() {
        fileSystemOperations.copy {
            from(parameters.fileToReverse)
            from(parameters.destinationDir)
            filter { line -> line.reversed() }
        }
    }
}

abstract class ReverseFiles @Inject constructor(private val workerExecutor: WorkerExecutor) : SourceTask() {

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun reverseFiles() {
        /*// Create a WorkQueue with process isolation
        val workQueue = workerExecutor.processIsolation() {
            // Configure the options for the forked process
            forkOptions {
                maxHeapSize = "512m"
                systemProperty("org.gradle.sample.showFileSize", "true")
            }
        }
        */

        // Create a WorkQueue to submit work items
        val workQueue = workerExecutor.noIsolation()

        // Create and submit a unit of work for each file
        source.forEach { file ->
            workQueue.submit(ReverseFile::class) {
                fileToReverse.set(file)
                destinationDir.set(outputDir)
            }
        }
        // Wait for all asynchronous work submitted to this queue to complete before continuing
        workQueue.await()
        logger.lifecycle("Created ${outputDir.get().asFile.listFiles()?.size} reversed files in ${outputDir.get().asFile.listFiles()?.size}")
    }
}