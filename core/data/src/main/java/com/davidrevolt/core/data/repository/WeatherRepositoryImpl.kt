package com.davidrevolt.core.data.repository

import com.davidrevolt.core.data.modelmapper.asExternalModel
import com.davidrevolt.core.network.AppNetworkDataSource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val networkDataSource: AppNetworkDataSource,
) : WeatherRepository{

    override suspend fun getWeatherByPlace(placeId: String) =
        networkDataSource.getWeatherByPlace(placeId).asExternalModel()

}