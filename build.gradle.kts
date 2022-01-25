buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libraries.kotlin)
        classpath(Libraries.gradle)
        classpath(Libraries.SqlDelight.sqlDelightPlugin)
        classpath(Libraries.kotlinSerialization)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}