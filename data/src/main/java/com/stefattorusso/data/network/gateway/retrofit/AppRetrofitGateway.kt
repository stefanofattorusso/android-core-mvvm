package com.stefattorusso.data.network.gateway.retrofit

import com.stefattorusso.data.entity.ImageEntity
import com.stefattorusso.data.network.gateway.AppGateway
import com.stefattorusso.data.network.gateway.retrofit.service.AppRetrofitService
import kotlinx.coroutines.Deferred

class AppRetrofitGateway(
    private val appRetrofitService: AppRetrofitService
) : AppGateway {

    override suspend fun retrieveListAsync(): Deferred<List<ImageEntity>> {
        return appRetrofitService.retrieveListAsync()
    }
}