package com.stefattorusso.domain.interactor.impl

import com.stefattorusso.domain.interactor.LoginUseCase
import com.stefattorusso.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    repository: AuthRepository
) : BaseUseCaseImpl<AuthRepository>(repository), LoginUseCase {

    override suspend fun execute(email: String, password: String) {
        return repository.loginWithEmailAndPassword(email, password)
    }
}