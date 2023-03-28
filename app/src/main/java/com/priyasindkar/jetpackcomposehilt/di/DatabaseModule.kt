package com.priyasindkar.jetpackcomposehilt.di

import android.content.Context
import androidx.room.Room
import com.priyasindkar.jetpackcomposehilt.data.AppDatabase
import com.priyasindkar.jetpackcomposehilt.data.NamesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNamesDao(appDatabase: AppDatabase): NamesDao {
        return appDatabase.namesDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "names_list.db"
        ).build()
    }
}