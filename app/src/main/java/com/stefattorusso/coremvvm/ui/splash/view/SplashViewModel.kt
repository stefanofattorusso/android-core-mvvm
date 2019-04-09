package com.stefattorusso.coremvvm.ui.splash.view

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.NoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val emailValid = MutableLiveData<Boolean>()
    private val passwordValid = MutableLiveData<Boolean>()

    private lateinit var auth: FirebaseAuth

    init {
        emailValid.value = true
        passwordValid.value = true
    }

    override fun onCreated() {
        super.onCreated()
        auth = FirebaseAuth.getInstance()
        checkUserStatus()
    }

    private fun checkUserStatus() {
        launchAction {
            uiState.value = Loading
            delay(2000)
            val hasSession = withContext(Dispatchers.IO) {
                auth.currentUser != null
            }
            uiState.value = if (hasSession) HasData else NoData
        }
    }
}