plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("guru.stefma.bintrayrelease")
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

    lintOptions {
        isAbortOnError = false
    }

}

androidExtensions {
    isExperimental = true
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

// ./gradlew clean build bintrayUpload -PbintrayUser=BINTRAY_USERNAME -PbintrayKey=BINTRAY_KEY -PdryRun=false
version = "0.1.2"
group = "io.github.tonnyl"
androidArtifact {
    artifactId = "whatsnew"
}
publish {
    userOrg = "tonnyl"
    desc = "WhatsNew automatically displays a short description of the new features when users update your app."
    website = "https://github.com/TonnyL/WhatsNew"
}
