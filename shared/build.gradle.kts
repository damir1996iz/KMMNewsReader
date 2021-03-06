plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    //iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation (Libraries.SqlDelight.sqlDelightRuntime)
                implementation (Libraries.Koin.koinCore)
                implementation (Libraries.Common.kotlinxCoroutinesCore)
                implementation (Libraries.SqlDelight.sqlDelightCoroutinesPlugin)
                implementation (Libraries.Ktor.ktorCore)
                implementation (Libraries.Ktor.ktorLogging)
                implementation (Libraries.Ktor.ktorSerialization)
                implementation (Libraries.Common.kotlinxDateTime)
                implementation (Libraries.Common.napier)
                implementation (Libraries.Common.logback)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation (Libraries.SqlDelight.sqlDelightAndroid)
                implementation (Libraries.Sqlite.SQLite)
                implementation (Libraries.Sqlite.SQLCipher)
                implementation (Libraries.Koin.koinAndroid)
                implementation (Libraries.KTXExtensions.ktxLifecycleViewModel)
                implementation (Libraries.Ktor.ktorAndroid)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation (Libraries.SqlDelight.sqlDelightNative)
                implementation (Libraries.Ktor.ktorIos)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

sqldelight {
    database("NewsDB") {
        packageName = "com.damikkg.kmmnewsapi.local.sqldelight"
        sourceFolders = listOf("kotlin")
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}