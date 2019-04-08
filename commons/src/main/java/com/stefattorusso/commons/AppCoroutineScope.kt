package com.stefattorusso.commons

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlin.coroutines.CoroutineContext

object AppCoroutineScope : CoroutineScope by GlobalScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main.immediate
}