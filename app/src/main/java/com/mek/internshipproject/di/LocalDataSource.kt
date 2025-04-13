package com.mek.internshipproject.di

import com.mek.internshipproject.model.Location
import com.mek.internshipproject.room.LocationDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val locationsDao : LocationDao) {

    fun getAllLocations() : Flow<List<Location>>{
        return locationsDao.getAllLocation()
    }

    suspend fun getLocation(locationId : Int) : Location{
        return locationsDao.getLocation(locationId)
    }

    suspend fun insertLocation(location : Location){
        locationsDao.insertLocation(location)
    }

    suspend fun deleteLocation(location : Location){
        locationsDao.deleteLocation(location)
    }
}