package com.davidrevolt.core.network.model.networkweather

data class Current(
    val cloud_cover: Int,
    val icon: String,
    val icon_num: Int,
    val precipitation: Precipitation,
    val summary: String,
    val temperature: Double,
    val wind: Wind
)