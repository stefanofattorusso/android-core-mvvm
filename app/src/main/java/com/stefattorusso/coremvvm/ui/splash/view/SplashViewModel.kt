package com.stefattorusso.coremvvm.ui.splash.view

import androidx.lifecycle.viewModelScope
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.utils.Error
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.NoData
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.interactor.HasSessionUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var hasSessionUseCase: HasSessionUseCase

    override fun onAttached() {
        checkUserStatus()
    }

    private fun checkUserStatus() {
        viewModelScope.launch {
            uiState.value = Loading
            delay(2000)
            when (val result = hasSessionUseCase.execute()) {
                is Outcome.Success -> uiState.value = if (result.value) HasData else NoData
                is Outcome.Error -> {
                    uiState.value = Error
                    error.value = result.cause
                }
            }
        }
    }
}