package com.stefattorusso.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.stefattorusso.data.coroutines.CoroutineDispatchers
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val dispatchers: CoroutineDispatchers
) : AuthRepository {

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Outcome<FirebaseUser> {
        return try {
            val result = withContext(dispatchers.io) {
                auth.signInWithEmailAndPassword(email, password).await()
            }
            Outcome.Success(result.user)
        } catch (e: Exception) {
            Outcome.Error(Outcome.FailureReason.UNKNOWN_ERROR, e)
        }
    }

    override suspend fun hasSession(): Outcome<Boolean> {
        return withContext(dispatchers.default) {
            Outcome.Success(auth.currentUser != null)
        }
    }
}