package com.stefattorusso.coremvvm.ui.login.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val loggedIn = MutableLiveData<Boolean>()

    init {
        loggedIn.value = false
    }

    fun isLoggedIn(): LiveData<Boolean> = loggedIn

    fun logout(){

    }

    fun loginWithFacebook(){

    }

    fun loginWithGoogle(){

    }

    fun loginWithEmail(){

    }
}