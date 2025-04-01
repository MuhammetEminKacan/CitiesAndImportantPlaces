package com.mek.internshipproject.retrofit

import com.mek.internshipproject.model.CityModel
import retrofit2.http.GET
import retrofit2.http.Url

interface CityApi {

    @GET
   suspend fun getCity(@Url url : String) : CityModel
}