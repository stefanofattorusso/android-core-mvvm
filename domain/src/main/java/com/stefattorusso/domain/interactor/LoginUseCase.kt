package com.stefattorusso.domain.interactor

import com.google.firebase.auth.FirebaseUser
import com.stefattorusso.domain.Outcome

interface LoginUseCase {
    suspend fun execute(email: String, password: String): Outcome<FirebaseUser>
}