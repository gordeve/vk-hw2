package com.gordeve.hw2.cataas

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

interface CataasApi {
    @GET("cat")
    suspend fun getCat(): Cat

    @GET("/api/cats")
    suspend fun getCats(@Query("skip") skip: Int, @Query("limit") limit: Int): List<Cat>
}

data class Cat(
    @SerializedName("_id") val id: String,
    val mimetype: String,
    val size: Int,
    val tags: List<String>
)