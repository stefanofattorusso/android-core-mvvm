package com.stefattorusso.coremvvm.ui.login.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val emailValid = MutableLiveData<Boolean>()
    private val passwordValid = MutableLiveData<Boolean>()

    init {
        emailValid.value = true
        passwordValid.value = true
    }

    fun isEmailValid(): LiveData<Boolean> = emailValid

    fun isPasswordValid(): LiveData<Boolean> = passwordValid

    fun loginUserWithEmail(email: String, password: String) {
        if (validateData(email, password)) {

        }
    }

    private fun validateData(email: String, password: String): Boolean {
        var valid = true
        if (email.isBlank()) {
            emailValid.value = false
            valid = false
        }
        if (password.isBlank()) {
            passwordValid.value = false
            valid = false
        }
        return valid
    }


}