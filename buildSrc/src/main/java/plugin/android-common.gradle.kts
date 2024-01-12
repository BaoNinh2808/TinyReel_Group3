package plugin

import AppConfig
import baseDependencies
import composeDependencies
import testDependencies


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.CompileSdk
    defaultConfig {
        minSdk = AppConfig.MinSdk
        consumerProguardFiles ("consumer-rules.pro")
    }

    buildTypes {
        testBuildType = "debug"
        debug {}
        release {}
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = AppConfig.JvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.KotlinCompilerExtensionVersion
    }

    packagingOptions {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    libraryVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir(File("build/generated/ksp/$name/kotlin"))
            }
        }
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {
    baseDependencies()
    composeDependencies()
    testDependencies()
}



