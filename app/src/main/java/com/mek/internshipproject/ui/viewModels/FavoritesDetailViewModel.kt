package com.mek.internshipproject.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mek.internshipproject.di.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesDetailViewModel @Inject constructor(
    application : Application,
    private val repo : Repository
) : AndroidViewModel(application) {

}