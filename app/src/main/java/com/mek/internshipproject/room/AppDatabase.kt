package com.mek.internshipproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mek.internshipproject.model.Location

@Database(entities = [Location::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun locationDao() : LocationDao
}