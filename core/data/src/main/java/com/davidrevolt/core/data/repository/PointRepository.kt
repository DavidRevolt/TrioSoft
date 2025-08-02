package com.davidrevolt.core.data.repository

import com.davidrevolt.core.model.Point
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface PointRepository {
    fun getPoints(): Flow<List<Point>>

    suspend fun create(
        humidity: Int,
        temperature: Int,
        date: Date
    )

    suspend fun sync()

}