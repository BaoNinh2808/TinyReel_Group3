plugins {
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}
//
android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    namespace = "com.example.commentlisting"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

}
//
//dependencies {
//
//    implementation("androidx.core:core-ktx:1.12.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.11.0")
//    implementation(project(":data"))
//    implementation(project(":core"))
//    implementation("androidx.navigation:navigation-common:2.7.6")
//    implementation("androidx.compose.foundation:foundation:1.6.0-rc01")
//    implementation("com.gitee.baijuncheng-open-source:coil:1.0.0")
//    implementation("io.coil-kt:coil:2.5.0")
//    implementation("com.google.android.libraries.navigation:navigation:5.1.2")
//    implementation("androidx.navigation:navigation-runtime-ktx:2.7.6")
//    implementation("com.google.accompanist:accompanist-navigation-material:0.28.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.2.0-alpha13")
//    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13")
//    implementation("androidx.hilt:hilt-navigation-compose:1.2.0-alpha01")
//    implementation("io.coil-kt:coil-compose:2.5.0")
//    implementation(project(":common:composable"))
//    implementation(project(":common:theme"))
//    implementation("androidx.compose.material3:material3:1.2.0-beta02")
//    implementation(project(":domain"))

//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//}

//plugins {
//    id("plugin.android-common")
//}


dependencies {
//    implementation ("com.google.dagger:hilt-android:2.45")
//    implementation("androidx.compose.material3:material3:1.1.2")
//    kapt ("com.google.dagger:hilt-compiler:2.45")
//    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
//    implementation("androidx.compose.foundation:foundation:1.6.0-rc01")
//    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13")
//    implementation("io.coil-kt:coil-compose:2.5.0")
    COMMON_THEME
    DOMAIN
    DATA
    CORE
    COMMON_COMPOSABLE
    baseDependencies()
    composeDependencies()
    testDependencies()
    media3Dependency()
}