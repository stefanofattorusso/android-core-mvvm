package com.stefattorusso.data.network.gateway

import com.stefattorusso.data.entity.ImageEntity
import com.stefattorusso.domain.Outcome

interface AppGateway {
    suspend fun retrieveListAsync(): Outcome<List<ImageEntity>>
}