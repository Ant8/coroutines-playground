package com.abm.ant8.coroutinesplayground

import androidx.annotation.IntRange
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object WrcResultsApi {
    const val baseUrl = "http://wrc-api.herokuapp.com/api/"

    interface Service {
        @GET("championship")
        fun getTopTenDriversOverallFor(@Query("year") @IntRange(from = 1978, to = 2019) season: Int): Call<DriverChampionshipsStandings>

        @GET("rally")
        fun getTopTenForRally(@Query("name") name: String, @Query("year") @IntRange(from = 1978, to = 2019) season: Int): Call<RallyResult>
    }

    val service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(Service::class.java)
}