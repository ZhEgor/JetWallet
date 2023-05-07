plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.zhiroke.presentation.auth"
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("default")
    productFlavors {
        register("prod")
        register("dev")
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

    implementation(project(":core:common"))
    implementation(project(":core:components"))
    implementation(project(":core:navigation"))
    implementation(project(":core:theme"))
    implementation(project(":domain"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.biometric)

    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.toolingPreview)
    implementation(Libs.Compose.material3)
    implementation(Libs.Lifecycle.runtimeCompose)
    implementation(Libs.Lifecycle.viewModelCompose)

    implementation(Libs.Coroutines.core)

    implementation(Libs.Koin.android)
    implementation(Libs.Koin.compose)
    implementation(Libs.Koin.core)
}