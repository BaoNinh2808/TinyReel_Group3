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
include(":data")
include(":feature:myprofile")
include(":feature:creatorprofile")
include(":domain")
include(":feature:search")
include(":feature:commentlisting")
include(":feature:setting")
