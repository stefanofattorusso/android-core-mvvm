package com.stefattorusso.data.network.gateway

import com.stefattorusso.data.entity.ImageEntity
import kotlinx.coroutines.Deferred

interface AppGateway {

    suspend fun retrieveListAsync(): Deferred<List<ImageEntity>>
}