plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.zhiroke.core.cardrecognition"
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
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

    implementation(project(":core:common"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.toolingPreview)
    implementation(Libs.Compose.material3)

    implementation(Libs.Coroutines.core)

    implementation(Libs.Gms.textRecognizerMlKit)

    implementation(Libs.CameraX.camera2)
    implementation(Libs.CameraX.view)
    implementation(Libs.CameraX.lifecycle)
    implementation(Libs.CameraX.mlKit)
}