plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":core:common"))
    implementation(project(":core:components"))
    implementation(project(":core:navigation"))
    implementation(project(":core:theme"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.Lifecycle.lifecycleKtx)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.toolingPreview)
    implementation(Libs.Compose.material3)
    androidTestImplementation(Libs.Test.androidJUnit)
    androidTestImplementation(Libs.Test.espresso)
    androidTestImplementation(Libs.Compose.testJunit4)
}