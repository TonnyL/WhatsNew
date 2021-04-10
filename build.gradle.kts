buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {

        classpath(Deps.GradlePlugin.android)
        classpath(Deps.GradlePlugin.kotlin)

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}