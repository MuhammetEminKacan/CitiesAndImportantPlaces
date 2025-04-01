package com.mek.internshipproject.retrofit

import com.mek.internshipproject.model.Data
import com.mek.internshipproject.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(CityApi::class.java)

    suspend fun getAllCity() : List<Data>{
        val pages = listOf(
            "https://storage.googleapis.com/invio-com/usg-challenge/city-location/page-1.json",
            "https://storage.googleapis.com/invio-com/usg-challenge/city-location/page-2.json",
            "https://storage.googleapis.com/invio-com/usg-challenge/city-location/page-3.json",
            "https://storage.googleapis.com/invio-com/usg-challenge/city-location/page-4.json"
        )

        val allData = mutableListOf<Data>()
        for (pageUrl in pages){
            val response = api.getCity(pageUrl)
            response.data?.let {
                allData.addAll(it.filterNotNull())
            }
        }
        return allData
    }

}