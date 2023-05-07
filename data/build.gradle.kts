plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.zhiroke.data"
    compileSdk = Configs.compileSdk

    defaultConfig {
        minSdk = Configs.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
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

    implementation(Libs.Room.runtime)
    implementation(Libs.Room.ktx)
    kapt(Libs.Room.compiler)
    implementation(Libs.SqlCipher.cipher)
    implementation(Libs.Security.crypto)

    implementation(Libs.Koin.core)
    implementation(Libs.Koin.android)

    androidTestImplementation(Libs.Test.jUnit)
    androidTestImplementation(Libs.Test.androidJUnit)
    androidTestImplementation(Libs.Test.googleTruth)
}