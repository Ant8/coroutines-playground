package com.abm.ant8.coroutinesplayground.view.seasonDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abm.ant8.coroutinesplayground.model.DriverChampionshipsStandings
import com.abm.ant8.coroutinesplayground.model.WrcResultsRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

class SeasonDetailsViewModel(private val season: Int) : ViewModel() {
    private val topTenDriversOverall = CompletableDeferred<DriverChampionshipsStandings>()
    val rallies = WrcResultsRepository.getRalliesFor(season)

    init {
        viewModelScope.launch {

            topTenDriversOverall.complete(WrcResultsRepository.getTopTenDriversOverallFor(season))
        }
    }

    suspend fun getTopTenDriversOverall() = topTenDriversOverall.await()
}
