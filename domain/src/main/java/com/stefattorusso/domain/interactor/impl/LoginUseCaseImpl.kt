package com.stefattorusso.domain.interactor.impl

import com.google.firebase.auth.FirebaseUser
import com.stefattorusso.domain.interactor.LoginUseCase
import com.stefattorusso.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    repository: AuthRepository
) : BaseUseCaseImpl<AuthRepository>(repository), LoginUseCase {

    override suspend fun execute(email: String, password: String): FirebaseUser {
        return repository.loginWithEmailAndPassword(email, password)
    }
}