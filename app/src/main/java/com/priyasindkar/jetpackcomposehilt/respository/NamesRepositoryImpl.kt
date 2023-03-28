package com.priyasindkar.jetpackcomposehilt.respository

import com.priyasindkar.jetpackcomposehilt.data.Names
import com.priyasindkar.jetpackcomposehilt.data.NamesDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class NamesRepositoryImpl @Inject constructor(private val namesDao: NamesDao) : NamesRepository {

    override fun addName(name: String) {
        namesDao.insertAll(Names(name))
    }

    override fun getAllNames(): Flow<List<Names>> = namesDao.getAll()
}