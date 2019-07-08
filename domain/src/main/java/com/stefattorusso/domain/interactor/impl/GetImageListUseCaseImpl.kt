package com.stefattorusso.domain.interactor.impl

import com.stefattorusso.domain.Image
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.interactor.GetImageListUseCase
import com.stefattorusso.domain.repository.ImageRepository
import javax.inject.Inject

class GetImageListUseCaseImpl @Inject constructor(
    private val imageRepository: ImageRepository
) : GetImageListUseCase {

    override suspend fun execute(): Outcome<List<Image>> {
        return imageRepository.retrieveList()
    }
}