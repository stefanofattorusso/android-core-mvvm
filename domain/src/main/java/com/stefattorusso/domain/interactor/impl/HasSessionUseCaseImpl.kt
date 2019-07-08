package com.stefattorusso.domain.interactor.impl

import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.interactor.HasSessionUseCase
import com.stefattorusso.domain.repository.AuthRepository
import javax.inject.Inject

class HasSessionUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : HasSessionUseCase {

    override suspend fun execute(): Outcome<Boolean> {
        return repository.hasSession()
    }
}