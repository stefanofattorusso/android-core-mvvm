package com.stefattorusso.domain.interactor

interface LoginUseCase {
    suspend fun execute(email: String, password: String)
}