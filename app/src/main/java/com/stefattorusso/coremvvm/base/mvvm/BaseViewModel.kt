package com.stefattorusso.coremvvm.base.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stefattorusso.coremvvm.data.mapper.ModelMappers
import com.stefattorusso.coremvvm.utils.ErrorHandler
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
    lateinit var mModelMappers: ModelMappers

    protected var uiState: MutableLiveData<UIState> = MutableLiveData()

    private val mJob: Job by lazy { Job() }

    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    open fun onCreated() {}

    override fun onCleared() {
        super.onCleared()
        mJob.cancel()
    }

    fun getUiState(): LiveData<UIState> = uiState

    protected fun launchAction(block: suspend CoroutineScope.() -> Unit) {
        launch(mExceptionHandler, block = block)
    }
}