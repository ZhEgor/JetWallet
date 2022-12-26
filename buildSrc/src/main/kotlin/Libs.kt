object Libs {

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.9.0"
        const val appCompat = "androidx.appcompat:appcompat:1.5.1"
        const val activityCompose = "androidx.activity:activity-compose:1.6.1"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val uiUtils = "androidx.compose.ui:ui-util:${Versions.compose}"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val material3 = "androidx.compose.material3:material3:${Versions.material3}"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
        const val accompanistPagerIndicator = "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
        const val testJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val testManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    }

    object Permissions {
        const val accompanist = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object Gms {
        const val textRecognizerMlKit = "com.google.android.gms:play-services-mlkit-text-recognition:${Versions.textRecognizerMlKit}"
    }

    object CameraX {
        const val mlKit = "androidx.camera:camera-mlkit-vision:${Versions.cameraXMlKit}"
        const val camera2 = "androidx.camera:camera-camera2:${Versions.cameraX}"
        const val lifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraX}"
        const val view = "androidx.camera:camera-view:${Versions.cameraX}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Coil {
        const val compose = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object SqlCipher {
        const val cipher = "net.zetetic:android-database-sqlcipher:${Versions.sqlcipher}"
    }

    object Navigation {
        const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigation}"
    }

    object Lifecycle {
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val runtimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    }

    object Test {
        const val jUnit = "junit:junit:4.13.2"
        const val androidJUnit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val googleTruth = "com.google.truth:truth:1.1.3"
    }
}