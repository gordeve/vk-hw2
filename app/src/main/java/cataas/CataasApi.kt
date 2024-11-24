package cataas

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CataasApi {
    @GET("cat")
    suspend fun getCat(): Response<Cat>

    @GET("/api/cats")
    suspend fun getCats(@Query("skip") skip: Int, @Query("limit") limit: Int): Response<List<Cat>>
}

data class Cat(
    @SerializedName("_id") val id: String,
    val mimetype: String,
    val size: Int,
    val tags: List<String>
)