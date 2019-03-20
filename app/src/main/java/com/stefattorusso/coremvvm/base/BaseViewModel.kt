package com.stefattorusso.coremvvm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stefattorusso.commons.ErrorData
import com.stefattorusso.commons.ErrorHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    val loader: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<ErrorData?> = MutableLiveData()

    private val mJob: Job by lazy { Job() }
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    private lateinit var mExceptionHandler: ErrorHandler

/*
    open fun onCreated(){
        mExceptionHandler = ErrorHandler(context, CoroutineExceptionHandler) { code, title, description ->
            hideProgress()
            showError(code, title, description)
        }
    }
*/

    override fun onCleared() {
        super.onCleared()
        mJob.cancel()
    }

    protected fun launchAction(block: suspend CoroutineScope.() -> Unit){
        launch(/*mExceptionHandler, */block = block)
    }
}