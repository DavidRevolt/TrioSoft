package com.davidrevolt.core.network

import com.davidrevolt.core.network.model.NetworkPoint
import kotlinx.coroutines.flow.Flow

interface AppNetworkDataSource {
    fun getPoints(userId: String): Flow<List<NetworkPoint>>
    suspend fun create(point: NetworkPoint)
}