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
include(":core:cardrecognition")
include(":domain")
include(":data")
include(":presentation:mywallet")
include(":presentation:auth")
