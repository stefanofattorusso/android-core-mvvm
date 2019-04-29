package com.stefattorusso.coremvvm.utils.coroutines

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CoroutineDispatchersImpl @Inject constructor() : CoroutineDispatchers {
    override val ui: CoroutineContext by lazy { Main }
    override val background: CoroutineContext by lazy { IO }
}