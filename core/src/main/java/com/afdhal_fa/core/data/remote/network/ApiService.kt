package com.afdhal_fa.core.data.remote.network

import com.afdhal_fa.core.data.remote.response.GameResponse
import com.afdhal_fa.core.data.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getList(
        @Query("page") page: Int,
        @Query("page_size") page_size: Int
    ): ListGameResponse

    @GET("games/{id}")
    suspend fun getDetail(@Path("id") id: String): GameResponse
}
