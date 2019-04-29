package com.stefattorusso.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository : Repository {

    suspend fun hasSession(): Boolean

    suspend fun loginWithEmailAndPassword(email: String, password: String): FirebaseUser?
}