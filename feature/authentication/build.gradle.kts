import com.android.build.api.dsl.ComposeOptions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {
    namespace = "com.example.authentication"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures{
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0-alpha01")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.6")
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-analytics")
//    implementation("com.google.firebase:firebase-core:16.0.1")
    implementation("com.google.firebase:firebase-auth:16.0.2")
    implementation("com.google.accompanist:accompanist-navigation-material:0.33.2-alpha")
    implementation(project(":core"))
    implementation("androidx.compose.foundation:foundation:1.6.0-beta03")
    implementation("androidx.compose.material3:material3-android:1.2.0-beta01")
    implementation(project(":common:composable"))
    implementation(project(":common:theme"))
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.5.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")
}