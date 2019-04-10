package com.stefattorusso.domain.interactor.impl

import com.stefattorusso.domain.interactor.HasSessionUseCase
import com.stefattorusso.domain.repository.AuthRepository
import javax.inject.Inject

class HasSessionUseCaseImpl @Inject constructor(
    repository: AuthRepository
) : BaseUseCaseImpl<AuthRepository>(repository), HasSessionUseCase {

    override suspend fun execute(): Boolean {
        return repository.hasSession()
    }
}