package com.damikkg.kmmnewsapi.di

import com.damikkg.kmmnewsapi.data.ILocalCache
import com.damikkg.kmmnewsapi.data.IRemoteSource
import com.damikkg.kmmnewsapi.data.local.LocalCacheImp
import com.damikkg.kmmnewsapi.data.local.sqldelight.DatabaseDriverFactory
import com.damikkg.kmmnewsapi.data.remote.RemoteSourceImp
import com.damikkg.kmmnewsapi.domain.INewsRepo
import com.damikkg.kmmnewsapi.domain.repository.NewsRepoImp
import com.damikkg.kmmnewsapi.feature.NewsViewModel
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            repoModule,
            platformModule()
        )
    }

//IOS
fun initKoin():KoinApplication = initKoin {}

val repoModule = module {
    single {
        HttpClient {
            install(JsonFeature)
            {
                val json = kotlinx.serialization.json.Json{
                    ignoreUnknownKeys = true
                }
                serializer = KotlinxSerializer(json)
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

    single<ILocalCache> {
        LocalCacheImp(get())
    }

    single<IRemoteSource> {
        RemoteSourceImp(get())
    }
    single<INewsRepo> {
        NewsRepoImp(get(), get())
    }
}

val viewModelModule = module {
    single {
        NewsViewModel()
    }
}