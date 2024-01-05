package com.davidrevolt.core.network

import android.util.Log
import com.davidrevolt.core.network.model.NetworkPoint
import com.davidrevolt.core.network.retrofit.RetrofitAppNetwork
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

const val POINTS_COLLECTION = "points"
const val USER_ID_FIELD = "userId"
const val DATE_FIELD = "date"
const val TEMPERATURE_FIELD = "temperature"
const val HUMIDITY_FIELD = "humidity"

class AppNetworkDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val retrofitAppNetwork: RetrofitAppNetwork
) :
    AppNetworkDataSource {

    // Firebase Methods
    override fun getPoints(userId: String): Flow<List<NetworkPoint>> =
        firestore.collection(POINTS_COLLECTION).whereEqualTo(USER_ID_FIELD, userId)
            .orderBy(DATE_FIELD, Query.Direction.ASCENDING).dataObjects()

    override suspend fun create(point: NetworkPoint) {
        Log.i("AppLog", "Network module is creating point...")
        //firestore.collection(POINTS_COLLECTION).add(point).await()
        val exists =
            firestore.collection(POINTS_COLLECTION).whereEqualTo(USER_ID_FIELD, point.userId)
                .whereEqualTo(DATE_FIELD, point.date).get().await().documents
        if (exists.isEmpty()) {
            firestore.collection(POINTS_COLLECTION).add(point).await()
        } else {
            Log.i("AppLog", "Network module found point with same date already exists, updating...")
            exists[0].reference.update(
                TEMPERATURE_FIELD,
                point.temperature,
                HUMIDITY_FIELD,
                point.humidity
            ).await()
        }
        Log.i("AppLog", "Network module is created point Successfully")
    }


    // Retrofit Methods
    override suspend fun getWeatherByPlace(placeId: String) =
        retrofitAppNetwork.getWeatherByPlace(placeId)
}