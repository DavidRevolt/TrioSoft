package com.davidrevolt.core.network.di

import com.davidrevolt.core.network.AppNetworkDataSource
import com.davidrevolt.core.network.AppNetworkDataSourceImpl
import com.davidrevolt.core.network.retrofit.RetrofitAppNetwork
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


const val BACKEND_URL = "https://www.meteosource.com/"
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

        @Provides
        @Singleton
        fun provideRetrofitAppNetwork(): RetrofitAppNetwork =
            Retrofit.Builder()
                .baseUrl(BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Gson Converter
                .build()
                .create(RetrofitAppNetwork::class.java) // The Interface
    }
}