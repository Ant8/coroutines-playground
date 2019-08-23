package com.abm.ant8.coroutinesplayground

import androidx.annotation.IntRange

object WrcResultsRepository {
    suspend fun getTopTenDriversOverallFor(@IntRange(from = 1978, to = 2019) season: Int): DriverChampionshipsStandings =
        WrcResultsApi.service.getTopTenDriversOverallFor(season)

    suspend fun getTopTenCrewsFor(rally: String, @IntRange(from = 1978, to = 2019) season: Int): RallyResult =
        WrcResultsApi.service.getTopTenFor(rally, season)

    fun getRalliesFor(@IntRange(from = 1978, to = 2019) season: Int) = ralliesFor[season]


}