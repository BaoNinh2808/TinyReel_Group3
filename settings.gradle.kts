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

rootProject.name = "TinyReel"
include(":app")
include(":feature")
include(":feature:home")
include(":feature:authentication")
include(":feature:profile")
include(":feature:post")
include(":feature:camera")
include(":common")
include(":common:composable")
include(":common:theme")
include(":core")
include(":dataa")
include(":lasaonua")
include(":data")
include(":domain")
include(":domain")
