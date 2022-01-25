package com.damikkg.kmmnewsapi.data.local.sqldelight

import android.content.Context
import com.damikkg.kmmnewsapi.local.sqldelight.NewsDB
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver():SqlDriver
    {
        return AndroidSqliteDriver(NewsDB.Schema,context,"newsDB")
    }
}