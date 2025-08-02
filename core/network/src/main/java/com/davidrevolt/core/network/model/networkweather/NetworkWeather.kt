package com.davidrevolt.core.network.model.networkweather

data class NetworkWeather(
    val current: Current,
    val daily: Any,
    val elevation: Int,
    val hourly: Any,
    val lat: String,
    val lon: String,
    val timezone: String,
    val units: String
)