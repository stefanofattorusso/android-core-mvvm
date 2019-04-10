package com.stefattorusso.coremvvm.ui.login.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.domain.interactor.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var loginUseCase: LoginUseCase

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
            launchAction {
                uiState.value = Loading
                withContext(Dispatchers.IO) {
                    loginUseCase.execute(email, password)
                }
                uiState.value = HasData
            }
        }
    }

    fun loginWithFacebook() {

    }

    fun loginWithGoogle() {

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