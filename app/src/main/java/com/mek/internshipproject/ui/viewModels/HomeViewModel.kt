package com.mek.internshipproject.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mek.internshipproject.di.Repository
import com.mek.internshipproject.model.Coordinates
import com.mek.internshipproject.model.Data
import com.mek.internshipproject.model.Location
import com.mek.internshipproject.retrofit.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,private val repo: Repository
) : AndroidViewModel(application) {
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

    fun insertLocation(description : String,name : String,image : String,coordinates : Coordinates,id : Int){
        viewModelScope.launch {
            repo.localData.insertLocation(location = Location(
                coordinates = coordinates,
                image = image,
                name = name,
                description = description,
                id = id
            )
            )
        }
    }
    fun deleteLocation(location: Location){
        viewModelScope.launch {
            repo.localData.deleteLocation(location)
        }
    }

    fun getAllFavoriteLocations() : LiveData<List<Location>>{
        return repo.localData.getAllLocations().asLiveData()
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