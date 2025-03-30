package com.mek.internshipproject.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("city")
    val city: String?,
    @SerializedName("locations")
    val locations: List<Location?>?
)