package com.davidrevolt.database.di

import com.davidrevolt.database.AppDatabase
import com.davidrevolt.database.dao.PointDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Singleton
    @Provides
    fun providePointDao(database: AppDatabase): PointDao =
        database.pointDao()
}