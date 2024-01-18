import AppConfig.CompileSdk
import AppConfig.JvmTarget
import AppConfig.KotlinCompilerExtensionVersion
import AppConfig.MinSdk
import AppConfig.TargetSdk
import AppConfig.VersionCode
import AppConfig.VersionName

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {
    namespace = "com.example.tinyreel"
    compileSdk = CompileSdk

    defaultConfig {
        applicationId = "com.example.tinyreel"
        minSdk = MinSdk
        targetSdk = TargetSdk
        versionCode = VersionCode
        versionName = VersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        jvmTarget = JvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = KotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/**/*"
        }
    }
}

dependencies {
    baseDependencies()
    composeDependencies()
    testDependencies()
//    moduleDependencies()
    CORE
    COMMON_THEME
    FEATURE_HOME
    FEATURE_POST
    FEATURE_MY_PROFILE
    FEATURE_CAMERA
    FEATURE_AUTHENTICATION
    FEATURE_SEARCH
    FEATURE_COMMENT_LISTING
}