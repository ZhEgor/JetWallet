plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.zhiroke.domain"
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
    implementation(project(":data"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.Lifecycle.runtimeKtx)

    implementation(Libs.Koin.core)
    implementation(Libs.Koin.android)

    testImplementation(Libs.Test.jUnit)
    androidTestImplementation(Libs.Test.androidJUnit)
    androidTestImplementation(Libs.Test.espresso)
    androidTestImplementation(Libs.Compose.testJunit4)
}