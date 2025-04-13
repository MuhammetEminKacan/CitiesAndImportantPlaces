package com.mek.internshipproject.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorites_place")
data class Location(
    @SerializedName("coordinates")
    val coordinates: Coordinates?,
    @SerializedName("description")
    val description: String?,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?
)