package com.mek.internshipproject.room

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mek.internshipproject.model.Coordinates

@TypeConverters
class TypeConverter {

    @TypeConverter
    fun fromCoordinatesToString(coordinates: Coordinates) : String?{
        return coordinates?.let {
            "${it.lat},${it.lng}"
        }
    }

    @TypeConverter
    fun fromStringToCoordinates(value : String?) : Coordinates?{
        return value?.let {
            val parts = it.split(",")
            Coordinates(
                lat = parts[0].toDouble(),
                lng = parts[1].toDouble()
            )
        }
    }



}