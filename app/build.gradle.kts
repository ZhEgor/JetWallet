plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = Configs.packageName
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.packageName
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:theme"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":features:mywallet"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.Lifecycle.runtimeKtx)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.material3)
    implementation(Libs.Navigation.navigationCompose)
    implementation(Libs.Koin.core)
    implementation(Libs.Koin.android)
}