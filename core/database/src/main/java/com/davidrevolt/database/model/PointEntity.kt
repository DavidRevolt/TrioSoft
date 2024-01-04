package com.davidrevolt.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "point")
data class PointEntity(
    @PrimaryKey
    val id: String = "",
    val humidity: Int,
    val temperature: Int,
    val date: Date,
    val userId: String = "",
)