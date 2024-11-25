package com.gordeve.hw2.cataas

import com.gordeve.hw2.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CataasService {
    private val cataasApi = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CataasApi::class.java)

    suspend fun getCat() = cataasApi.getCat()
    suspend fun getCats(skip: Int, limit: Int) = cataasApi.getCats(skip, limit)
}