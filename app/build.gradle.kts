plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myspinnercalender"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myspinnercalender"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true   // Enable Jetpack Compose
        viewBinding = true  // Enable view binding
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"  // Specify the Compose version

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    //Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Compose Dependencies
    implementation("androidx.compose.ui:ui:1.5.0")  // Compose UI
    implementation("androidx.compose.material:material:1.5.0")  // Compose Material
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")  // Compose tooling
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")
    implementation(libs.androidx.ui.tooling.preview.android)  // Compose lifecycle

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}