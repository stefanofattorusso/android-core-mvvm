package com.stefattorusso.domain.interactor.impl

import com.stefattorusso.domain.Image
import com.stefattorusso.domain.interactor.GetImageListUseCase
import com.stefattorusso.domain.repository.ImageRepository
import javax.inject.Inject

class GetImageListUseCaseImpl @Inject constructor(
    imageRepository: ImageRepository
) : BaseUseCaseImpl<ImageRepository>(imageRepository), GetImageListUseCase {

    override suspend fun execute(): List<Image> {
        return repository.retrieveList()
    }
}