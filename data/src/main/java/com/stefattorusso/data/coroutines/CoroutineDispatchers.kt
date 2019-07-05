package com.stefattorusso.data.coroutines

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {

    val ui: CoroutineContext

    val io: CoroutineContext

    val default: CoroutineContext
}
