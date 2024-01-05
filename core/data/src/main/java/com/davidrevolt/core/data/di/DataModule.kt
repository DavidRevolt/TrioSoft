package com.davidrevolt.core.data.di

import com.davidrevolt.core.data.repository.PointRepository
import com.davidrevolt.core.data.repository.PointRepositoryImpl
import com.davidrevolt.core.data.repository.WeatherRepository
import com.davidrevolt.core.data.repository.WeatherRepositoryImpl
import com.davidrevolt.core.data.utils.authentication.AuthenticationService
import com.davidrevolt.core.data.utils.authentication.AuthenticationServiceImpl
import com.davidrevolt.core.data.utils.snackbarmanager.SnackbarManager
import com.davidrevolt.core.data.utils.snackbarmanager.SnackbarManagerImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    // Repos
    @Binds
    abstract fun bindsPointRepository(pointRepositoryImpl: PointRepositoryImpl): PointRepository

    @Binds
    abstract fun bindsWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindsAuthenticationService(authenticationServiceImpl: AuthenticationServiceImpl): AuthenticationService

    @Binds
    abstract fun bindsSnackbarManager(snackbarManagerImpl: SnackbarManagerImpl): SnackbarManager

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    }
}