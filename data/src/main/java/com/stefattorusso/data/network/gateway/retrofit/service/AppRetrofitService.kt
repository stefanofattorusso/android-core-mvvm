package com.stefattorusso.data.network.gateway.retrofit.service

import com.stefattorusso.data.entity.ImageEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface AppRetrofitService {

    @GET("v2/list")
    suspend fun retrieveImageList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<ImageEntity>
}