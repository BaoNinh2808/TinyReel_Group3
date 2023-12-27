import Versions.ActivityCompose
import Versions.AppCompat
import Versions.ComposeBom
import Versions.CoreKtx
import Versions.EspressoCore
import Versions.HiltAndroidVersion
import Versions.HiltNavigationCompose
import Versions.JunitExtKtx
import Versions.LifecycleRunTimeKtx
import Versions.NavigationCompose
import Versions.SplashScreenApi
import Versions.accompanist

object Versions {
    // Version of dependencies
    const val CoreKtx = "1.12.0"
    const val LifecycleRunTimeKtx = "2.6.1"
    const val AppCompat = "1.6.1"
    const val SplashScreenApi = "1.1.0-alpha02"

    const val ActivityCompose = "1.7.0"
    const val ComposeBom = "2023.08.00"

    const val foundation = "1.6.0-beta03"

    const val ui = "1.6.0-beta03"
    const val ui_graphics = "1.6.0-beta03"
    const val ui_tooling_preview = "1.6.0-beta03"

    const val material3 = "1.2.0-beta01"
    const val material = "1.6.0-beta03"

    const val HiltAndroidVersion = "2.50"
    const val HiltNavigationCompose = "1.2.0-alpha01"

    const val navigation_runtime_ktx = "2.7.6"
    const val NavigationCompose = "2.7.6"

    const val accompanist = "0.33.2-alpha"

    const val JunitExtKtx = "1.1.5"
    const val EspressoCore = "3.5.1"
    const val test_ui_test_junit4 = "1.6.0-beta03"

    const val debug_ui_tooling = "1.6.0-beta03"
    const val debug_ui_test_manifest = "1.6.0-beta03"
}

object Deps {
    // Dependencies and split them follow each group
    object AndroidX{
        const val coreKtx = "androidx.core:core-ktx:$CoreKtx"
        const val appCompat = "androidx.appcompat:appcompat:$AppCompat"
        const val lifecycleRunTimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$LifecycleRunTimeKtx"
        const val splashScreen = "androidx.core:core-splashscreen:$SplashScreenApi"
    }

    object Compose {
        const val composeBom = "androidx.compose:compose-bom:$ComposeBom"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val composeMaterial3 = "androidx.compose.material3:material3"
        const val composeMaterial = "androidx.compose.material:material"
        const val composeFoundation = "androidx.compose.foundation:foundation"
        const val composeRuntime = "androidx.compose.runtime:runtime"
        const val composeActivity = "androidx.activity:activity-compose:$ActivityCompose"
        const val composeUiUtil = "androidx.compose.ui:ui-util"
    }

    object Accompanist {
        const val systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:$accompanist"
        const val navigationMaterial = "com.google.accompanist:accompanist-navigation-material:$accompanist"
        const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:$accompanist"
        const val permission = "com.google.accompanist:accompanist-permissions:$accompanist"
    }

    object Navigation {
        const val navigationCompose = "androidx.navigation:navigation-compose:$NavigationCompose"

    }
    object Test {
        const val espressorCore = "androidx.test.espresso:espresso-core:$EspressoCore"
        const val junitExtKtx = "androidx.test.ext:junit-ktx:$JunitExtKtx"
    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:$HiltAndroidVersion"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$HiltAndroidVersion"

        //hilt compose
        const val hiltNavigationCompse = "androidx.hilt:hilt-navigation-compose:$HiltNavigationCompose"
    }
}