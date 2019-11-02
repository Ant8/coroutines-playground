package com.abm.ant8.coroutinesplayground.model

import androidx.annotation.IntRange
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object WrcResultsApi {
    const val baseUrl = "http://wrc-api.herokuapp.com/api/"

    interface Service {
        @GET("championship")
        suspend fun getTopTenDriversOverallFor(@Query("year") @IntRange(from = 1978, to = 2019) season: Int): DriverChampionshipsStandings

        @GET("rally")
        suspend fun getTopTenFor(@Query("name") rallyName: String, @Query("year") @IntRange(from = 1978, to = 2019) season: Int): RallyResult

    }

    private val networkClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    val service: Service = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(networkClient)
        .build()
        .create(Service::class.java)
}