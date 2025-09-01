package com.tandroid.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.tandroid.sqldelight.NoteDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver() : SqlDriver =
        NativeSqliteDriver(NoteDatabase.Schema, "notes_database.db")
}