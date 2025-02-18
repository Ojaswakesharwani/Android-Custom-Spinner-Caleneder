/*// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra["kotlin_version"] = "1.9.0"  // Define Kotlin version here

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${property("kotlin_version")}")
        classpath("com.android.tools.build:gradle:8.8.0")  // Use the correct version of AGP
    }
}*/

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}