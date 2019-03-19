package com.stefattorusso.domain.interactor

import com.stefattorusso.domain.Image

interface GetImageListUseCase {
    suspend fun execute(): List<Image>
}