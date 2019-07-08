package com.stefattorusso.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.stefattorusso.domain.Outcome

interface AuthRepository : Repository {

    suspend fun hasSession(): Outcome<Boolean>

    suspend fun loginWithEmailAndPassword(email: String, password: String): Outcome<FirebaseUser>
}