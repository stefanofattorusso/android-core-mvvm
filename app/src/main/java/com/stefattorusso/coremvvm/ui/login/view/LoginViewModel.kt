package com.stefattorusso.coremvvm.ui.login.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.utils.Error
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.ValidatorHelper
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.interactor.LoginUseCase
import kotlinx.coroutines.launch
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
            triggerLogin(email, password)
        }
    }

    private fun triggerLogin(email: String, password: String) {
        viewModelScope.launch {
            uiState.value = Loading
            when (val result = loginUseCase.execute(email, password)) {
                is Outcome.Success -> uiState.value = HasData
                is Outcome.Error -> {
                    error.value = result.cause
                    uiState.value = Error
                }
            }
        }
    }

    fun loginWithFacebook() {

    }

    fun loginWithGoogle() {

    }

    private fun validateData(email: String, password: String): Boolean {
        var valid = true
        when {
            email.isBlank() -> {
                emailValid.value = false
                valid = false
            }
            !ValidatorHelper.validateEmail(email) -> {
                emailValid.value = false
                valid = false
            }
            else -> emailValid.value = true
        }
        if (password.isBlank()) {
            passwordValid.value = false
            valid = false
        } else passwordValid.value = true

        return valid
    }
}