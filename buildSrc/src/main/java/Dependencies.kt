object Versions {

    const val compileSdk = 29
    const val targetSdk = 29
    const val appMinSdk = 23
    const val libMinSdk = 16

    const val androidGradle = "4.1.3"
    const val kotlin = "1.4.32"
    const val appcompat = "1.3.0-rc01"
    const val recyclerView = "1.2.0"

    const val androidTestCore = "1.4.0-alpha05"
    const val androidTestRunner = "1.4.0-alpha05"
    const val androidTestRules = "1.4.0-alpha05"
    const val androidExtJunit = "1.1.3-alpha05"
    const val espressoCore = "3.4.0-alpha05"

}

object Deps {

    object GradlePlugin {

        const val android = "com.android.tools.build:gradle:${Versions.androidGradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    }

    object Kotlin {

        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    }

    object AndroidX {

        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    }

    object AndroidTest {

        const val core = "androidx.test:core:${Versions.androidTestCore}"
        const val runner = "androidx.test:runner:${Versions.androidTestRunner}"
        const val rules = "androidx.test:rules:${Versions.androidTestRules}"
        const val extJunit = "androidx.test.ext:junit:${Versions.androidExtJunit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    }

}
