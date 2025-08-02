package com.davidrevolt.database.converter

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun longToDate(value: Long?): Date? =
        value?.let { Date(it * 1000) }

    @TypeConverter //MUST USE SECONDS Not milli!! , sqlite cant compare timestamps in milliseconds!!!!
    fun dateToLong(date: Date?): Long? =
        date?.toInstant()?.epochSecond

}