package com.stefattorusso.coremvvm.base.mvvm

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.utils.ErrorHandler
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    @Inject
    lateinit var mExceptionHandler: ErrorHandler
    @Inject
    lateinit var mImageModelMapper: ImageModelMapper

    var uiState = MutableLiveData<UIState>().apply { this.value = Loading }

    private val mJob: Job by lazy { Job() }

    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    @CallSuper
    open fun onCreated() {
        mExceptionHandler.setUiState(uiState)
    }

    override fun onCleared() {
        super.onCleared()
        mJob.cancel()
    }

    protected fun launchAction(block: suspend CoroutineScope.() -> Unit) {
        launch(mExceptionHandler, block = block)
    }
}