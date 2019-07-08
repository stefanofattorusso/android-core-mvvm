package com.stefattorusso.domain.interactor.impl

import com.google.firebase.auth.FirebaseUser
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.interactor.LoginUseCase
import com.stefattorusso.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : LoginUseCase {

    override suspend fun execute(email: String, password: String): Outcome<FirebaseUser> {
        return repository.loginWithEmailAndPassword(email, password)
    }
}