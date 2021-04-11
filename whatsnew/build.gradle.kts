import org.jetbrains.kotlin.konan.properties.loadProperties
import org.jetbrains.kotlin.konan.properties.propertyString
import java.net.URI

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    `maven-publish`
    id("signing")
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

// Disable creating javadocs
task<Javadoc>("withType") {
    isEnabled = false
}

val androidSourcesJar by tasks.register<Jar>("androidSourcesJar") {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

var signingKeyId = ""
var signingPassword = ""
var signingSecretKeyRingFile = ""
var ossrhUsername = ""
var ossrhPassword = ""

val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    val properties = loadProperties("${rootDir}/local.properties")
    signingKeyId = properties.propertyString("signing.keyId") ?: ""
    signingPassword = properties.propertyString("signing.password") ?: ""
    signingSecretKeyRingFile = properties.propertyString("signing.secretKeyRingFile") ?: ""
    ossrhUsername = properties.propertyString("ossrhUsername") ?: ""
    ossrhPassword = properties.getProperty("ossrhPassword")
} else {
    println("No props file, loading env vars")
    signingKeyId = System.getenv("SIGNING_KEY_ID") ?: ""
    signingPassword = System.getenv("SIGNING_PASSWORD") ?: ""
    signingSecretKeyRingFile = System.getenv("SIGNING_SECRET_KEY_RING_FILE") ?: ""
    ossrhUsername = System.getenv("OSSRH_USERNAME") ?: ""
    ossrhPassword = System.getenv("OSSRH_PASSWORD") ?: ""
}

ext["signing.keyId"] = signingKeyId
ext["signing.password"] = signingPassword
ext["signing.secretKeyRingFile"] = signingSecretKeyRingFile
ext["ossrhUsername"] = ossrhUsername
ext["ossrhPassword"] = ossrhPassword

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "io.github.tonnyl"
            artifactId = "whatsnew"
            version = "0.1.3"

            artifact("$buildDir/outputs/aar/${project.getName()}-release.aar")
            artifact(androidSourcesJar)

            pom {
                name.set("WhatsNew")
                description.set("WhatsNew automatically displays a short description of the new features when users update your app.")
                url.set("https://github.com/TonnyL/WhatsNew")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("TonnyL")
                        name.set("Li Zhao Tai Lang")
                        email.set("lizhaotailang@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/TonnyL/WhatsNew.git")
                    developerConnection.set("scm:git:ssh://github.com/TonnyL/WhatsNew.git")
                    url.set("https://github.com/TonnyL/WhatsNew")
                }
                withXml {
                    val dependenciesNode = asNode().appendNode("dependencies")

                    project.configurations.implementation.allDependencies.forEach {
                        val dependencyNode = dependenciesNode.appendNode("dependency")
                        dependencyNode.appendNode("groupId", it.group)
                        dependencyNode.appendNode("artifactId", it.name)
                        dependencyNode.appendNode("version", it.version)
                    }
                }
            }
        }
    }
    repositories {
        maven {
            name = "mavencentral" // Sonatype / MavenCentral
            val releasesRepoUrl = "https://s01.oss.sonatype.org/content/repositories/releases/"
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

signing {
    sign(publishing.publications)
}