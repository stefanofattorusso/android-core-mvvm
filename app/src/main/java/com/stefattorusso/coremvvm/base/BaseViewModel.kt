package com.stefattorusso.coremvvm.base

import androidx.lifecycle.ViewModel
import com.stefattorusso.coremvvm.utils.ErrorHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    @Inject
    lateinit var mExceptionHandler: ErrorHandler

    private val mJob: Job by lazy { Job() }

    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    open fun onCreated() {}

    override fun onCleared() {
        super.onCleared()
        mJob.cancel()
    }

    protected fun launchAction(block: suspend CoroutineScope.() -> Unit) {
        launch(mExceptionHandler, block = block)
    }
}