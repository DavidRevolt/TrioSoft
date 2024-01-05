package com.davidrevolt.core.data.repository

import android.util.Log
import com.davidrevolt.core.data.modelmapper.asEntity
import com.davidrevolt.core.data.modelmapper.asExternalModel
import com.davidrevolt.core.data.utils.authentication.AuthenticationService
import com.davidrevolt.core.model.Point
import com.davidrevolt.core.network.AppNetworkDataSource
import com.davidrevolt.core.network.model.NetworkPoint
import com.davidrevolt.database.dao.PointDao
import com.davidrevolt.database.model.PointEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(
    private val networkDataSource: AppNetworkDataSource,
    private val pointDao: PointDao,
    authenticationService: AuthenticationService
) : PointRepository {

    private val userId = authenticationService.currentUserId

    override fun getPoints(): Flow<List<Point>> =
        pointDao.getPointEntities(userId = userId)
            .map { it.map(PointEntity::asExternalModel) }


    override suspend fun create(
        humidity: Int,
        temperature: Int,
        date: Date
    ) {
        networkDataSource.create(
            NetworkPoint(
                humidity = humidity,
                temperature = temperature,
                date = date,
                userId = userId
            )
        )
    }


    override suspend fun sync() {
        Log.d("AppLog", "Point repository is syncing")
        networkDataSource.getPoints(userId).collect { it ->
            it.map {
                Log.d("AppLog", "Got Point ${it.id} from firestore/firestore cache, upsert in dao")
                pointDao.upsert(it.asEntity())
            }
        }
    }


}