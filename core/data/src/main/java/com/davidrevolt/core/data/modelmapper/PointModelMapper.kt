package com.davidrevolt.core.data.modelmapper

import com.davidrevolt.core.model.Point
import com.davidrevolt.core.network.model.NetworkPoint
import com.davidrevolt.database.model.PointEntity

/**
 *  Map between models defined in different modules.
 */
fun NetworkPoint.asEntity() =
    PointEntity(
        id = id,
        humidity = humidity,
        temperature = temperature,
        date = date,
        userId = userId
    )

fun PointEntity.asExternalModel() =
    Point(id = id, humidity = humidity, temperature = temperature, date = date)