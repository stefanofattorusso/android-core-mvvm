package com.stefattorusso.domain.interactor

import com.stefattorusso.domain.Outcome

interface HasSessionUseCase {
    suspend fun execute(): Outcome<Boolean>
}