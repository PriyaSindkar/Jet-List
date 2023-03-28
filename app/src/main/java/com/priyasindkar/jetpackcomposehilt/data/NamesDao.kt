package com.priyasindkar.jetpackcomposehilt.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NamesDao {

    @Query("SELECT * FROM names ORDER BY id DESC")
    fun getAll(): Flow<List<Names>>

    @Insert
    fun insertAll(vararg names: Names)

    @Query("DELETE FROM names")
    fun deleteAll()
}
