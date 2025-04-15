package com.mek.internshipproject.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CityModel(
    @SerializedName("currentPage")
    val currentPage: Int?,
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("itemPerPage")
    val itemPerPage: Int?,
    @SerializedName("pageSize")
    val pageSize: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("totalPage")
    val totalPage: Int?
) : Serializable