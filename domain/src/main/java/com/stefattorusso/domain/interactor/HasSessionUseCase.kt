package com.stefattorusso.domain.interactor

interface HasSessionUseCase {
    suspend fun execute(): Boolean
}