package com.abm.ant8.coroutinesplayground.view.seasonList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SeasonListViewModel : ViewModel() {
    private val seasons = CompletableDeferred<List<Int>>()

    init {
        viewModelScope.launch {
            delay(500)
            seasons.complete((1979..2019).toList())
        }
    }

    suspend fun getSeasons() = seasons.await()
}
