package com.stefattorusso.domain.interactor

import com.google.firebase.auth.FirebaseUser

interface LoginUseCase {
    suspend fun execute(email: String, password: String): FirebaseUser?
}