package com.priyasindkar.jetpackcomposehilt.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * SQLite Database for storing the logs.
 */
@Database(entities = [Names::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun namesDao(): NamesDao
}
