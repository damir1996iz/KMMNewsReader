plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.damikkg.kmmnewsapi.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Libraries.Compose.material)
    implementation(Libraries.Compose.activity)
    implementation(Libraries.Compose.navigation)
    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.uiToolingPreview)

    implementation(Libraries.Android.appCompat)
    implementation(Libraries.Android.constraintLayout)

    implementation(Libraries.Android.coil)


    implementation(Libraries.Koin.koinCore)
    implementation(Libraries.Koin.koinAndroid)
}