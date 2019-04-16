package com.stefattorusso.coremvvm.ui.splash.view

import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.NoData
import com.stefattorusso.domain.interactor.HasSessionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var hasSessionUseCase: HasSessionUseCase

    override fun onCreated() {
        super.onCreated()
        checkUserStatus()
    }

    private fun checkUserStatus() {
        launchAction {
            uiState.value = Loading
            delay(2000)
            val hasSession = withContext(Dispatchers.IO) {
                hasSessionUseCase.execute()
            }
            uiState.value = if (hasSession) HasData else NoData
        }
    }
}