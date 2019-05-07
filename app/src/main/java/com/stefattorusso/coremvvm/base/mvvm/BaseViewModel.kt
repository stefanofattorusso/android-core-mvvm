package com.stefattorusso.coremvvm.base.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stefattorusso.coremvvm.utils.Error
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.UIState
import com.stefattorusso.coremvvm.utils.coroutines.CoroutineDispatchers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    @Inject
    lateinit var dispatcher: CoroutineDispatchers

    var uiState = MutableLiveData<UIState>().apply { this.value = Loading }
    var error = MutableLiveData<Throwable>()

    private val mJob: Job by lazy { Job() }
    private val handler = CoroutineExceptionHandler { _, exception ->
        uiState.value = Error
        error.value = exception
    }

    override val coroutineContext: CoroutineContext
        get() = mJob + dispatcher.ui + handler

    open fun onCreated() {}

    fun getError(): LiveData<Throwable> = error

    override fun onCleared() {
        mJob.cancel()
        super.onCleared()
    }

    protected fun launchAction(block: suspend CoroutineScope.() -> Unit) {
        launch(block = block)
    }
}