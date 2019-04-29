package com.stefattorusso.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.stefattorusso.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun loginWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun hasSession(): Boolean {
        return auth.currentUser != null
    }
}