import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.baseDependencies() {
    implementation(platform(Deps.Compose.composeBom))
    implementation(Deps.Compose.composeActivity)
    implementation(Deps.Compose.composeUi)
    implementation(Deps.Compose.composeUiToolingPreview)
    implementation(Deps.Compose.composeUiUtil)
    implementation(Deps.Compose.composeFoundation)
    implementation(Deps.Compose.composeRuntime)
    implementation(Deps.Compose.composeMaterial3)
    implementation(Deps.Compose.composeMaterial)

    //navigation
    implementation(Deps.Navigation.navigationCompose)

    //hilt navigation
    implementation(Deps.Hilt.hiltNavigationCompse)

    //accompanist
    accompanistDependencies()
}

fun DependencyHandler.composeDependencies() {
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.lifecycleRunTimeKtx)
    implementation(Deps.AndroidX.splashScreen)
    implementation(Deps.Hilt.hiltAndroid)
    implementation(Deps.Hilt.hiltCompiler)
}

fun DependencyHandler.accompanistDependencies() {
    implementation(Deps.Accompanist.systemuicontroller)
    implementation(Deps.Accompanist.navigationMaterial)
    implementation(Deps.Accompanist.navigationAnimation)
    implementation(Deps.Accompanist.permission)
}

fun DependencyHandler.testDependencies() {
    androidTestImplementation(Deps.Test.espressorCore)
    androidTestImplementation(Deps.Test.junitExtKtx)
}

fun DependencyHandler.moduleDependencies() {
    DATA
    CORE
    COMMON_THEME
    COMMON_COMPOSABLE
    FEATURE_HOME
    FEATURE_AUTHENTICATION
    FEATURE_MY_PROFILE
}


val DependencyHandler.DATA
    get() = implementation(project(mapOf("path" to ":data")))

val DependencyHandler.CORE
    get() = implementation(project(mapOf("path" to ":core")))

val DependencyHandler.COMMON_COMPOSABLE
    get() = implementation(project(mapOf("path" to ":common:composable")))

val DependencyHandler.COMMON_THEME
    get() = implementation(project(mapOf("path" to ":common:theme")))

val DependencyHandler.FEATURE_HOME
    get() = implementation(project(mapOf("path" to ":feature:home")))

val DependencyHandler.FEATURE_POST
    get() = implementation(project(mapOf("path" to ":feature:post")))

val DependencyHandler.FEATURE_AUTHENTICATION
    get() = implementation(project(mapOf("path" to ":feature:authentication")))

val DependencyHandler.FEATURE_MY_PROFILE
    get() = implementation(project(mapOf("path" to ":feature:profile")))