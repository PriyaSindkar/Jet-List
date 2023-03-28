package com.priyasindkar.jetpackcomposehilt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyasindkar.jetpackcomposehilt.data.Names
import com.priyasindkar.jetpackcomposehilt.respository.NamesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: NamesRepositoryImpl) :
    ViewModel() {
    var listOfNames: Flow<List<Names>> = repository.getAllNames()

    fun addName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addName(name)
        }
    }
}