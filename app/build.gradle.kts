plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.notes"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.notes"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

// Room library for database operations
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

// JUnit for unit testing
    testImplementation("junit:junit:4.13.2")

// AndroidJUnitRunner for Android instrumented testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

// AppCompat library for backward-compatible UI components
    implementation("androidx.appcompat:appcompat:1.6.1")

// Material Components library for modern UI components
    implementation("com.google.android.material:material:1.11.0")

// Espresso Core for UI testing
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

// Core KTX for Kotlin extensions
    implementation("androidx.core:core-ktx:1.12.0")

// ConstraintLayout for creating complex layouts
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

// Navigation Component for managing navigation within the app
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

// ViewModel KTX for easy integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

// Compose integration for ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

// LiveData KTX for easy integration with LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

// Kotlin Coroutines for asynchronous programming
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// RecyclerView Animators for adding animations to RecyclerView items
    implementation("jp.wasabeef:recyclerview-animators:4.0.2")

// Dagger Hilt for dependency injection
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")





}