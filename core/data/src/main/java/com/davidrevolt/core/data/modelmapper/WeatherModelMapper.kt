package com.davidrevolt.core.data.modelmapper

import com.davidrevolt.core.model.Weather
import com.davidrevolt.core.network.model.networkweather.NetworkWeather

/**
 *  Map between models defined in different modules.
 */
fun NetworkWeather.asExternalModel() =
    Weather(temperature = current.temperature, description = current.summary)