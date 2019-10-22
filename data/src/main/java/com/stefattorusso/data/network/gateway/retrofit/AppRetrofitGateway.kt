package com.stefattorusso.data.network.gateway.retrofit

import com.stefattorusso.data.coroutines.CoroutineDispatchers
import com.stefattorusso.data.entity.ImageEntity
import com.stefattorusso.data.network.gateway.AppGateway
import com.stefattorusso.data.network.gateway.retrofit.service.AppRetrofitService
import com.stefattorusso.domain.Outcome
import kotlinx.coroutines.withContext

class AppRetrofitGateway(
    private val appRetrofitService: AppRetrofitService,
    private val dispatchers: CoroutineDispatchers
) : AppGateway {

    override suspend fun retrieveListAsync(): Outcome<List<ImageEntity>> {
        return withContext(dispatchers.io) {
            try {
                Outcome.Success(appRetrofitService.retrieveImageList(1, 100))
            } catch (e: Exception) {
                Outcome.Error(Outcome.FailureReason.NETWORK_ERROR)
            }
        }
    }
}