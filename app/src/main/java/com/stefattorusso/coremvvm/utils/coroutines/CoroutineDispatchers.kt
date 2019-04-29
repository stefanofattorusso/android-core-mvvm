package com.stefattorusso.coremvvm.utils.coroutines

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {

    val ui: CoroutineContext

    val background: CoroutineContext
}
