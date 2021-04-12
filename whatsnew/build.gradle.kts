import org.jetbrains.kotlin.konan.properties.loadProperties
import org.jetbrains.kotlin.konan.properties.propertyString
import java.net.URI

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    id("org.jetbrains.dokka")
    id("com.vanniktech.maven.publish")
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

var ossrhUsername = ""
var ossrhPassword = ""

val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    val properties = loadProperties("${rootDir}/local.properties")
    ossrhUsername = properties.propertyString("ossrhUsername") ?: ""
    ossrhPassword = properties.propertyString("ossrhPassword") ?: ""
} else {
    println("No props file, loading env vars")
    ossrhUsername = System.getenv("OSSRH_USERNAME") ?: ""
    ossrhPassword = System.getenv("OSSRH_PASSWORD") ?: ""
}

publishing {
    repositories {
        withType<MavenArtifactRepository>() {
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            url = URI(
                    if (version.toString().endsWith("SNAPSHOT")) {
                        snapshotsRepoUrl
                    } else {
                        releasesRepoUrl
                    }
            )

            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
}

mavenPublish {
    releaseSigningEnabled = true
    nexus {
        baseUrl = "https://s01.oss.sonatype.org" // defaults to "https://oss.sonatype.org/service/local/"
        stagingProfile = "io.github.tonnyl" // defaults to the SONATYPE_STAGING_PROFILE Gradle property or the GROUP Gradle Property if not set
        repositoryUsername = ossrhUsername // defaults to the mavenCentralRepositoryUsername Gradle Property
        repositoryPassword = ossrhPassword // defaults to the mavenCentralRepositoryPassword Gradle Property
    }
}