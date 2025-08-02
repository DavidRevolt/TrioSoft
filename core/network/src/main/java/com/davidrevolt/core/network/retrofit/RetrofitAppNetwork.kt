package com.davidrevolt.core.network.retrofit

import com.davidrevolt.core.network.model.networkweather.NetworkWeather
import retrofit2.http.GET
import retrofit2.http.Query


const val SECTIONS = "current"
const val LANGUAGE = "en"
const val UNITS = "metric"
const val API_KEY = "INSERT METASOURCE API KEY HERE!!!"

interface RetrofitAppNetwork {
    @GET(value = "api/v1/free/point?")
    suspend fun getWeatherByPlace(
        @Query("place_id") placeId:String,
        @Query("sections") sections: String = SECTIONS,
        @Query("language") language: String = LANGUAGE,
        @Query("units") units: String = UNITS,
        @Query("key") apiKey: String = API_KEY
    ): NetworkWeather
}