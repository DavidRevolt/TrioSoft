package com.davidrevolt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davidrevolt.database.converter.DateConverter
import com.davidrevolt.database.dao.PointDao
import com.davidrevolt.database.model.PointEntity

@Database(
    entities = [PointEntity::class],
    version = 1
)
//Converters In Use For RoomDB complex objects
@TypeConverters(
    DateConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    //List Of Dao's
    abstract fun pointDao(): PointDao
}