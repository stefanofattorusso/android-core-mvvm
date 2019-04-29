package com.stefattorusso.coremvvm.utils

import com.stefattorusso.coremvvm.utils.coroutines.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TestCoroutineDispatchersImpl @Inject constructor() : CoroutineDispatchers {
    override val ui: CoroutineContext by lazy { Unconfined }
    override val background: CoroutineContext by lazy { Unconfined }
}