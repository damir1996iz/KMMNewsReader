package com.damikkg.kmmnewsapi.di

import com.damikkg.kmmnewsapi.data.local.sqldelight.DatabaseDriverFactory
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory() }
}.also {
    Napier.base(DebugAntilog())
}