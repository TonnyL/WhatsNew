plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(Versions.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.libMinSdk)
        targetSdkVersion(Versions.targetSdk)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            postprocessing {
                isRemoveUnusedCode = false
                isRemoveUnusedResources = false
                isObfuscate = false
                isOptimizeCode = false
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }
        }
    }

    buildFeatures {
        viewBinding = true
    }

    lintOptions {
        isAbortOnError = false
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.recyclerView)
    implementation(Deps.Kotlin.stdlib)

    androidTestImplementation(Deps.AndroidTest.core)
    androidTestImplementation(Deps.AndroidTest.runner)
    androidTestImplementation(Deps.AndroidTest.rules)
    androidTestImplementation(Deps.AndroidTest.extJunit)
    androidTestImplementation(Deps.AndroidTest.espressoCore)
}

repositories {
    mavenCentral()
}

// Disable creating javadocs
task<Javadoc>("withType") {
    isEnabled = false
}
