package com.abm.ant8.coroutinesplayground.view.seasonDetails.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abm.ant8.coroutinesplayground.view.seasonDetails.SeasonDetailsViewModel

class SeasonDetailsViewModelFactory(private val season: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(SeasonDetailsViewModel::class.java))
            SeasonDetailsViewModel(season) as T
        else throw IllegalArgumentException("wrong class type")
}