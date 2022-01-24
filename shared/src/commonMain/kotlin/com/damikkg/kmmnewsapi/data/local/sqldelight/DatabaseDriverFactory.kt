package com.damikkg.kmmnewsapi.data.local.sqldelight

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}