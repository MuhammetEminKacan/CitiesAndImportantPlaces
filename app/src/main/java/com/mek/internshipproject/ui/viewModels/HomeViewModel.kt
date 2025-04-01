package com.mek.internshipproject.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mek.internshipproject.model.Data
import com.mek.internshipproject.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val repository = RetrofitInstance()
    val cityDataList = MutableLiveData<List<Data>>()
    val isLoading = MutableLiveData<Boolean>()
    val erorMessage = MutableLiveData<String>()

    fun fetchCityLocations(){
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val data = repository.getAllCity()
                cityDataList.postValue(data)
            }catch (e : Exception){
                println(e.message.toString())
                erorMessage.value = "veri çekme hatası oldu : ${e.message}"
            }finally {
                isLoading.postValue(false)
            }
        }
    }

    fun observeCityDataList() : LiveData<List<Data>>{
        return cityDataList
    }

    fun observeIsLoading() : LiveData<Boolean>{
        return isLoading
    }

    fun observeErorMessage() : LiveData<String>{
        return erorMessage
    }
}