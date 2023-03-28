package com.priyasindkar.jetpackcomposehilt.respository

import com.priyasindkar.jetpackcomposehilt.data.Names
import kotlinx.coroutines.flow.Flow

interface NamesRepository {

    fun addName(name: String)

    fun getAllNames(): Flow<List<Names>>
}