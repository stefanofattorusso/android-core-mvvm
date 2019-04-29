package com.stefattorusso.domain.repository

interface AuthRepository : Repository {

    suspend fun hasSession(): Boolean

    suspend fun loginWithEmailAndPassword(email: String, password: String)
}