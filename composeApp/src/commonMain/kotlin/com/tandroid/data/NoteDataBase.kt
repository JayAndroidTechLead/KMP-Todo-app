package com.tandroid.data

import app.cash.sqldelight.db.SqlDriver
import com.tandroid.sqldelight.NoteDatabase

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DatabaseDriverFactory): NoteDatabase {
    val driver = driverFactory.createDriver()
    return NoteDatabase(driver)
}