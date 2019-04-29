package com.stefattorusso.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.stefattorusso.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun loginWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        return auth.signInWithEmailAndPassword(email, password).result?.user
    }

    override suspend fun hasSession(): Boolean {
        return auth.currentUser != null
    }
}