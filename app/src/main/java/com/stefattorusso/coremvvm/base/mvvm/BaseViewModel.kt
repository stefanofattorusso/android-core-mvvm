package com.stefattorusso.coremvvm.base.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stefattorusso.coremvvm.utils.Error
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.UIState
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    var uiState = MutableLiveData<UIState>().apply { this.value = Loading }
    var error = MutableLiveData<Throwable>()

    private val mJob: Job by lazy { Job() }
    private val handler = CoroutineExceptionHandler { _, exception ->
        uiState.value = Error
        error.value = exception
        mJob.cancel()
    }


    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main + handler

    open fun onCreated() {}

    fun getError(): LiveData<Throwable> = error

    override fun onCleared() {
        mJob.cancel()
        super.onCleared()
    }

    protected fun launchAction(block: suspend CoroutineScope.() -> Unit) {
        launch(/*handler,*/ block = block)
    }
}