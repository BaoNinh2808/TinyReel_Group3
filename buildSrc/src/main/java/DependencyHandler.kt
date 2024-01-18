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

fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

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
    implementation(Deps.Compose.constraintLayoutCompose)
    implementation(Deps.Compose.composeIcons)

    media3Dependency()

    //navigation
    implementation(Deps.Navigation.navigationCompose)

    //hilt navigation
    implementation(Deps.Hilt.hiltNavigationCompse)

    //coil
    implementation(Deps.Coil.coilCompose)
    implementation(Deps.Coil.coilVideo)

    //accompanist
    accompanistDependencies()

    //debug
    debugImplementation(Deps.Debug.ui_tooling)
    debugImplementation(Deps.Debug.ui_test_manifest)
}

fun DependencyHandler.composeDependencies() {
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.coreKtx)
    implementation(Deps.AndroidX.lifecycleRunTimeKtx)
    implementation(Deps.AndroidX.splashScreen)
    implementation(Deps.Hilt.hiltAndroid)
    kapt(Deps.Hilt.hiltCompiler)
}

fun DependencyHandler.firebase(){
    implementation(platform(Deps.Firebase.Firebase_bom))
    implementation(Deps.Firebase.firebase_analytics)
    implementation(Deps.Firebase.firebase_auth)
    implementation(Deps.Firebase.firebase_database)
}
fun DependencyHandler.accompanistDependencies() {
    implementation(Deps.Accompanist.systemuicontroller)
    implementation(Deps.Accompanist.navigationMaterial)
    implementation(Deps.Accompanist.navigationAnimation)
    implementation(Deps.Accompanist.permission)
}

fun DependencyHandler.media3Dependency() {
    implementation(Deps.AudioVideo.exoplayer)
    implementation(Deps.AudioVideo.expplayerDash)
    implementation(Deps.AudioVideo.media3Ui)
}
fun DependencyHandler.cameraXDependencies() {
    implementation(Deps.CameraX.cameraCore)
    implementation(Deps.CameraX.camera2)
    implementation(Deps.CameraX.cameraLifecycle)
    implementation(Deps.CameraX.cameraVideo)
    implementation(Deps.CameraX.cameraView)
    implementation(Deps.CameraX.CameraExt)
}
fun DependencyHandler.testDependencies() {
    androidTestImplementation(Deps.Test.espressorCore)
    androidTestImplementation(Deps.Test.junitExtKtx)
}

fun DependencyHandler.googleDependencies(){
    implementation(Deps.Google.material)
}
fun DependencyHandler.devDependencies(){
    implementation(Deps.Dev.dev)
}
fun DependencyHandler.moduleDependencies() {
    DATA
    CORE
    COMMON_THEME
    COMMON_COMPOSABLE
    FEATURE_HOME
    FEATURE_AUTHENTICATION
    FEATURE_MY_PROFILE
    FEATURE_CAMERA
}


val DependencyHandler.DATA
    get() = implementation(project(mapOf("path" to ":data")))

val DependencyHandler.CORE
    get() = implementation(project(mapOf("path" to ":core")))
val DependencyHandler.DOMAIN
    get() = implementation(project(mapOf("path" to ":domain")))
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
    get() = implementation(project(mapOf("path" to ":feature:myprofile")))

val DependencyHandler.FEATURE_CREATOR_PROFILE
    get() = implementation(project(mapOf("path" to ":feature:creatorprofile")))

val DependencyHandler.FEATURE_CAMERA
    get() = implementation(project(mapOf("path" to ":feature:camera")))

val DependencyHandler.FEATURE_SEARCH
    get() = implementation(project(mapOf("path" to ":feature:search")))

val DependencyHandler.FEATURE_COMMENT_LISTING
    get() = implementation(project(mapOf("path" to ":feature:commentlisting")))