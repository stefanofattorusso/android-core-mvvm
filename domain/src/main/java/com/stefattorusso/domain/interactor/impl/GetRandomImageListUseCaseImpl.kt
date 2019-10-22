package com.stefattorusso.domain.interactor.impl

import com.stefattorusso.domain.Image
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.interactor.GetRandomImageListUseCase
import com.stefattorusso.domain.repository.ImageRepository
import javax.inject.Inject

class GetRandomImageListUseCaseImpl @Inject constructor(
    private val imageRepository: ImageRepository
) : GetRandomImageListUseCase {

    override suspend fun execute(): Outcome<List<Image>> {
        return when (val result = imageRepository.retrieveList()) {
            is Outcome.Success -> Outcome.Success(result.value.shuffled())
            is Outcome.Error -> result
        }
    }
}