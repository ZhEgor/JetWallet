import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
}

val signingProps = Properties()
val singingPropertiesFile = rootProject.file("app/signing-configs.properties")

if (singingPropertiesFile.exists()) {
    signingProps.load(FileInputStream(singingPropertiesFile))
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
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    signingConfigs {
        register("prod") {
            storeFile = signingProps["prodSigningFileName"]?.let(rootProject::file)
            storePassword = "${signingProps["prodKeystorePassword"]}"
            keyAlias = "${signingProps["prodAlias"]}"
            keyPassword = "${signingProps["prodAliasPassword"]}"
        }
        register("dev") {
            storeFile = signingProps["devSigningFileName"]?.let(rootProject::file)
            storePassword = "${signingProps["devKeystorePassword"]}"
            keyAlias = "${signingProps["devAlias"]}"
            keyPassword = "${signingProps["devAliasPassword"]}"
        }
    }

    flavorDimensions("default")
    productFlavors {
        register("prod") {
            dimension = "default"
            resValue("string", "app_name", "JetWallet")
            signingConfig = signingConfigs.getByName("prod")
        }
        register("dev") {
            dimension = "default"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "JetWallet Dev")
            signingConfig = signingConfigs.getByName("dev")
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
    implementation(project(":core:common"))
    implementation(project(":core:theme"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation:mywallet"))
    implementation(project(":presentation:auth"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.Lifecycle.runtimeKtx)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.material3)
    implementation(Libs.Navigation.navigationCompose)
    implementation(Libs.Koin.core)
    implementation(Libs.Koin.android)
}