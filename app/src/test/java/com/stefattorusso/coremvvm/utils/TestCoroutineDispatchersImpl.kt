package com.stefattorusso.coremvvm.utils

import com.stefattorusso.data.coroutines.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TestCoroutineDispatchersImpl @Inject constructor() : CoroutineDispatchers {
    override val io: CoroutineContext by lazy { Unconfined }
    override val default: CoroutineContext by lazy { Unconfined }
    override val ui: CoroutineContext by lazy { Unconfined }
}