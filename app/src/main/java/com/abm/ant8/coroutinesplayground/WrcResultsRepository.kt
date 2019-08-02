package com.abm.ant8.coroutinesplayground

import androidx.annotation.IntRange
import retrofit2.await

object WrcResultsRepository {
    suspend fun getTopTenDriversOverallFor(@IntRange(from = 1978, to = 2019) season: Int): DriverChampionshipsStandings =
        WrcResultsApi.service.getTopTenDriversOverallFor(season).await()

    suspend fun getTopTenCrewsFor(rally: String, @IntRange(from = 1978, to = 2019) season: Int): RallyResult =
        WrcResultsApi.service.getTopTenFor(rally, season).await()
}