package com.stefattorusso.data.coroutines

import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CoroutineDispatchersImpl @Inject constructor() : CoroutineDispatchers {
    override val ui: CoroutineContext by lazy { Main }
    override val io: CoroutineContext by lazy { IO }
    override val default: CoroutineContext by lazy { Default }
}