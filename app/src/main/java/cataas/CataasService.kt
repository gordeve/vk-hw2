package cataas

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CataasService {
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://cataas.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val cataasApi = retrofitBuilder.create(CataasApi::class.java)

    suspend fun getCat() = cataasApi.getCat().body()
    suspend fun getCats(skip: Int, limit: Int) = cataasApi.getCats(skip, limit).body()
}