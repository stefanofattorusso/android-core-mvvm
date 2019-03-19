package com.stefattorusso.data.network.gateway.retrofit.service

import com.stefattorusso.data.entity.ImageEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface AppRetrofitService {

    @GET("list")
    fun retrieveListAsync(): Deferred<List<ImageEntity>>
}