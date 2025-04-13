package com.mek.internshipproject.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mek.internshipproject.di.Repository
import com.mek.internshipproject.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    application: Application,
    private val repo : Repository
) : AndroidViewModel(application) {

    val favoritesPlace =repo.localData.getAllLocations().asLiveData()

    fun deleteLocation(location: Location){
        viewModelScope.launch {
            repo.localData.deleteLocation(location)
        }
    }
}