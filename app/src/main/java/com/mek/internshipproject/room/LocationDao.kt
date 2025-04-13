package com.mek.internshipproject.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mek.internshipproject.model.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM favorites_place")
    fun getAllLocation() : Flow<List<Location>>

    @Query("SELECT * FROM favorites_place WHERE id = :locationId")
    fun getLocation(locationId : Int) :Location

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location : Location)

    @Delete
    suspend fun deleteLocation(location : Location)
}