rootProject.name = "Intelli-Auto-HMI"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

dependencyResolutionManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

include(":launcher_app")
include(":common")
include(":features:example")
include(":features:chargingPreferences")
include(":features:adas")
include(":features:status")
include(":features:climate")
include(":features:vcis")
include(":features:userprofiles")
