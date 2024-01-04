package com.davidrevolt.database.dao


import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.davidrevolt.database.model.PointEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PointDao{

    @Query("SELECT * FROM point WHERE userId = :userId ORDER BY date ASC")
    fun getPointEntities(userId:String): Flow<List<PointEntity>>

    @Upsert
    suspend fun upsert(pointEntity: PointEntity)

    @Query("DELETE FROM point")
    suspend fun deleteAllPoints()

}