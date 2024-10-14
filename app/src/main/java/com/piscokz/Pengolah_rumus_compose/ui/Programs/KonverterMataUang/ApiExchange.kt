package com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterMataUang

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

data class ExchangeRatesResponse (
    val rates : Map<String, Double>,
    val base : String,
    val to : String,
//    val symbols: List<String>
)

interface ExchangeRatesApi {
    @GET("latest")
    suspend fun  getLatestRates(
        @Query("access_key") apiKey : String,
        @Query("from") from : String,
        @Query("to") to : String,
        @Query("amount") amount : Int,
    ) : ExchangeRatesResponse
}

object RetrofitInstance {
    val api : ExchangeRatesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRatesApi::class.java)
    }
}