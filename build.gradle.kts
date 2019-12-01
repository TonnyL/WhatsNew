buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {

        classpath(Deps.GradlePlugin.android)
        classpath(Deps.GradlePlugin.kotlin)
        classpath(Deps.GradlePlugin.android)
        classpath(Deps.GradlePlugin.bintrayRelease)

    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}