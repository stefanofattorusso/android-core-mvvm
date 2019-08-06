package com.stefattorusso.coremvvm.base.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stefattorusso.coremvvm.utils.UIState

abstract class BaseViewModel : ViewModel() {

    var uiState: MutableLiveData<UIState> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    open fun onAttached() {}

    fun getError(): LiveData<Throwable> = error

    fun getUiState(): LiveData<UIState> = uiState
}