package com.stefattorusso.data.network.gateway.retrofit.service

import com.stefattorusso.data.entity.ImageEntity
import retrofit2.http.GET

interface AppRetrofitService {

    @GET("list")
    suspend fun retrieveImageList(): List<ImageEntity>
}