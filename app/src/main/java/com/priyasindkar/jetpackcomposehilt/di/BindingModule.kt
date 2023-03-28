package com.priyasindkar.jetpackcomposehilt.di

import com.priyasindkar.jetpackcomposehilt.respository.NamesRepository
import com.priyasindkar.jetpackcomposehilt.respository.NamesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class BindingModule {

    @Singleton
    @Binds
    abstract fun bindRepository(impl: NamesRepositoryImpl) : NamesRepository
}