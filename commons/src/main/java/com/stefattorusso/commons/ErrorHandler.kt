package com.stefattorusso.commons

import android.content.Context
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

class ErrorHandler(
        val context: Context,
        override val key: CoroutineContext.Key<*>,
        private val onError: (code: Int, title: String?, description: String?) -> Unit
) : CoroutineExceptionHandler {

//    private val errorManager = ErrorManager(context.resources)

    override fun handleException(context: CoroutineContext, exception: Throwable) {
//        errorManager.parseError(exception)
//        onError(errorManager.code, errorManager.title, errorManager.message)
    }
}