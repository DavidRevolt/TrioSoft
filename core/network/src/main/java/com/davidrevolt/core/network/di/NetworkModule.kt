package com.davidrevolt.core.network.di

import com.davidrevolt.core.network.AppNetworkDataSource
import com.davidrevolt.core.network.AppNetworkDataSourceImpl
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    // Binding
    @Binds
    abstract fun bindsAppNetworkDataSource(appNetworkDataSourceImpl: AppNetworkDataSourceImpl): AppNetworkDataSource

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore
    }
}