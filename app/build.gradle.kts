plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.tinyreel"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tinyreel"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/**/*"
        }
    }
}

dependencies {
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.50")
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0-alpha01")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    // implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    // implementation("com.google.firebase:firebase-analytics")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.dagger:hilt-android:2.49")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    implementation(project(":common:theme"))
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.6")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation(project(":core"))
    implementation(project(":feature:home"))
    implementation("com.google.accompanist:accompanist-adaptive:0.33.2-alpha")
    implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha")
    implementation("com.google.accompanist:accompanist-navigation-material:0.33.2-alpha")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.33.2-alpha")
    implementation("androidx.compose.material:material:1.6.0-beta03")
    implementation(project(":feature:authentication"))
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.compose.foundation:foundation:1.4.0-rc01")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}