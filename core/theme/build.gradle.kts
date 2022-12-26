
import plugins.HelloTask
import plugins.IncrementalReverseTask
import plugins.ReverseFiles
import plugins.UrlVerify

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.zhiroke.core.theme"
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.material3)
}

tasks.register<HelloTask>(name = "hello") {
    shouldRunAfter("build")
}.get()

tasks.register<IncrementalReverseTask>(name = "incrementalReverse") {
    inputDir.set(file("inputs"))
    outputDir.set(file("$buildDir/outputs"))
    inputProperty.set(project.findProperty("taskInputProperty") as String? ?: "original")
}

tasks.register("updateInputs") {
    outputs.dir("inputs")
    doLast {
        file("inputs/1.txt").writeText("Changed content for existing file 1.")
        file("inputs/4.txt").writeText("Content for new file 4.")
    }
}

tasks.register("removeOutput") {
    outputs.dir("$buildDir/outputs")
    doLast {
        file("$buildDir/outputs/1.txt").delete()
    }
}

tasks.register<UrlVerify>("verifyUrl")

tasks.register<ReverseFiles>("reverseFiles") {
    outputs.dir("$buildDir/outputs")
}
