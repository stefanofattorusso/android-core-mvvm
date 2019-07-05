package com.stefattorusso.domain

sealed class Outcome<out T : Any> {
    data class Success<out T : Any>(val value: T) : Outcome<T>()
    data class Error(val reason: FailureReason, val cause: Exception? = null) : Outcome<Nothing>()

    enum class FailureReason {
        NOT_FOUND,
        UNKNOWN_ERROR,
        NETWORK_ERROR
    }
}