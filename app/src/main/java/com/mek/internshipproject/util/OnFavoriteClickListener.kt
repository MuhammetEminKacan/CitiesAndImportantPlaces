package com.mek.internshipproject.util

import com.mek.internshipproject.model.Location

interface OnFavoriteClickListener {
    fun onFavoriteClick(location: Location,isFavorite : Boolean)
}