package com.davidrevolt.core.data.repository

import com.davidrevolt.core.model.Weather

interface WeatherRepository{

    suspend fun getWeatherByPlace(placeId: String): Weather
}