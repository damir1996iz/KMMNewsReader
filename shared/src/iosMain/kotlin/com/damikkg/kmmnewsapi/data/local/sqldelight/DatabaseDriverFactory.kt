package com.damikkg.kmmnewsapi.data.local.sqldelight

import com.damikkg.kmmnewsapi.local.sqldelight.NewsDB
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver
    {
        return NativeSqliteDriver(NewsDB.Schema,"newsDB")
    }
}