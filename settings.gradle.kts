pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "JetWallet"
include(":app")
include(":core:common")
include(":core:components")
include(":core:navigation")
include(":core:theme")
include(":domain")
include(":data")
include(":features:mywallet")
include(":features:cardrecognition")
include(":features:auth")
