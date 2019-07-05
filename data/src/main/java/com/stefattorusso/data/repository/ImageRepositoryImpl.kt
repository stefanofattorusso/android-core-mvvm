package com.stefattorusso.data.repository

import com.stefattorusso.data.entity.mapper.transform
import com.stefattorusso.data.network.gateway.AppGateway
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val appGateway: AppGateway
) : ImageRepository {

    override suspend fun retrieveList(): Outcome<List<Image>> {
        return when (val result = appGateway.retrieveListAsync()) {
            is Outcome.Success -> Outcome.Success(result.value.map { it.transform() })
            is Outcome.Error -> result
        }
    }
}