plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.zhiroke.features.mywallet"
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
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
        freeCompilerArgs = freeCompilerArgs + "-P"
        freeCompilerArgs = freeCompilerArgs + ("plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + project.buildDir.absolutePath + "/compose_metrics")
        freeCompilerArgs = freeCompilerArgs + "-P"
        freeCompilerArgs = freeCompilerArgs + ("plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + project.buildDir.absolutePath + "/compose_metrics")
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":core:common"))
    implementation(project(":core:components"))
    implementation(project(":core:navigation"))
    implementation(project(":core:theme"))
    implementation(project(":features:cardrecognition"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.Lifecycle.runtimeKtx)
    implementation(Libs.Lifecycle.runtimeCompose)

    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.toolingPreview)
    implementation(Libs.Compose.material3)
    implementation(Libs.Compose.accompanistPager)
    implementation(Libs.Compose.materialIconsExtended)
    implementation(Libs.Compose.materialIconsExtended)

    implementation(Libs.Coil.compose)

    implementation(Libs.Koin.core)
    implementation(Libs.Koin.android)
    implementation(Libs.Koin.compose)

    testImplementation(Libs.Test.jUnit)
    androidTestImplementation(Libs.Test.androidJUnit)
    androidTestImplementation(Libs.Test.espresso)
    androidTestImplementation(Libs.Compose.testJunit4)
}