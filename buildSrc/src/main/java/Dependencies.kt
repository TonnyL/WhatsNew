object Versions {

    val compileSdk = 29
    val targetSdk = 29
    val appMinSdk = 23
    val libMinSdk = 16

    val androidGradle = "4.0.1"
    val kotlin = "1.3.50"
    val bintrayRelease = "1.1.2"
    val appcompat = "1.1.0"
    val recyclerView = "1.1.0-rc01"

    val androidTestCore = "1.2.1-alpha02"
    val androidTestRunner = "1.3.0-alpha02"
    val androidTestRules = "1.3.0-alpha02"
    val androidExtJunit = "1.1.2-alpha02"
    val espressoCore = "3.3.0-alpha02"

}

object Deps {

    object GradlePlugin {

        val android = "com.android.tools.build:gradle:${Versions.androidGradle}"
        val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        val bintrayRelease = "guru.stefma.bintrayrelease:bintrayrelease:${Versions.bintrayRelease}"

    }

    object Kotlin {

        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    }

    object AndroidX {

        val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    }

    object AndroidTest {

        val core = "androidx.test:core:${Versions.androidTestCore}"
        val runner = "androidx.test:runner:${Versions.androidTestRunner}"
        val rules = "androidx.test:rules:${Versions.androidTestRules}"
        val extJunit = "androidx.test.ext:junit:${Versions.androidExtJunit}"
        val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    }

}
