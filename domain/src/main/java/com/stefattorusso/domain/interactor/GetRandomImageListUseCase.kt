package com.stefattorusso.domain.interactor

import com.stefattorusso.domain.Image
import com.stefattorusso.domain.Outcome

interface GetRandomImageListUseCase {
    suspend fun execute(): Outcome<List<Image>>
}