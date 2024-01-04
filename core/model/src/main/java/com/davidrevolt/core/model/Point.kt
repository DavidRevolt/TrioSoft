package com.davidrevolt.core.model

import java.util.Date

data class Point(
    val id: String = "",
    val humidity: Int,
    val temperature: Int,
    val date: Date
)
