package com.example.roomcompose.Object

import com.example.roomcompose.Internal.RawgApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    /**
     * Base URL dari API RAWG.
     */
    private const val BASE_URL = "https://api.rawg.io/api/"

    /**
     * Instance Retrofit yang dikonfigurasi dengan GsonConverterFactory.
     */
    val instance: RawgApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RawgApiService::class.java)
    }
}
