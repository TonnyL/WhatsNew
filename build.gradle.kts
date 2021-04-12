buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {

        classpath(Deps.GradlePlugin.android)
        classpath(Deps.GradlePlugin.kotlin)
        classpath(Deps.GradlePlugin.dokka)
        classpath(Deps.GradlePlugin.mavenPublish)

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}