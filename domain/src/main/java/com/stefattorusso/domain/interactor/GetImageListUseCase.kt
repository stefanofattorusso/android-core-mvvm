package com.stefattorusso.domain.interactor

import com.stefattorusso.domain.Image
import com.stefattorusso.domain.Outcome

interface GetImageListUseCase {
    suspend fun execute(): Outcome<List<Image>>
}