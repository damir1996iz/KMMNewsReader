object App {
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val minSDK = 23
    const val compileSDK = 31
    const val targetSDK = 31
}

object Versions {
    const val kotlin = "1.6.10"
    const val gradle = "7.1.0-rc01"
    const val sqlDelight = "1.5.3"

    const val material = "1.5.0"

    const val activityCompose = "1.4.0"
    const val compose = "1.2.0-alpha01"
    const val navigation = "2.4.0-rc01"
    const val appCompat = "1.4.1"
    const val constraintLayout = "2.1.3"
    const val ktxExtensions = "2.2.0"

    const val coroutines = "1.5.2"
    const val koin       = "3.1.2"
    const val ktor       = "1.6.3"

    const val kotlinxSerializationCore = "1.2.2"
    const val kotlinxCoroutinesCore = "1.6.0-native-mt"
    const val kotlinxDateTime = "0.3.2"

    const val sqlite = "2.2.0"
    const val sqlcipher = "4.5.0"

    const val coil = "1.4.0"
    const val napierVersion = "2.3.0"
}

object Libraries {
    const val kotlin              = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val gradle              = "com.android.tools.build:gradle:${Versions.gradle}"

    const val coroutinesCore    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    object Sqlite {
        const val SQLite = "androidx.sqlite:sqlite:${Versions.sqlite}"
        const val SQLCipher = "net.zetetic:android-database-sqlcipher:${Versions.sqlcipher}"
    }

    object SqlDelight {
        const val sqlDelightPlugin  = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        const val sqlDelightRuntime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val sqlDelightNative  = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        const val sqlDelightAndroid = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val sqlDelightCoroutinesPlugin = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
    }

    object Ktor {
        const val ktorCore          = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val ktorAndroid       = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val ktorIos           = "io.ktor:ktor-client-ios:${Versions.ktor}"
    }

    object Koin {
        const val koinAndroid       = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinCore          = "io.insert-koin:koin-core:${Versions.koin}"
    }

    object Compose {
        const val activity         = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val ui               = "androidx.compose.ui:ui:${Versions.compose}"
        const val material         = "androidx.compose.material:material:${Versions.compose}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val navigation       = "androidx.navigation:navigation-compose:${Versions.navigation}"
    }

    object Common {
        const val kotlinxSerializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerializationCore}"
        const val kotlinxCoroutinesCore    = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutinesCore}"
        const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"

        const val napier = "io.github.aakira:napier:${Versions.napierVersion}"
    }

    object Android {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object KTXExtensions {
        const val  ktxLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ktxExtensions}"
    }
}