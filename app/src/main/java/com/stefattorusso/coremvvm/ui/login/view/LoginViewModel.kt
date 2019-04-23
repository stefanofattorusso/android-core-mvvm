package com.stefattorusso.coremvvm.ui.login.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.utils.Constants.VALID_EMAIL_ADDRESS_REGEX
import com.stefattorusso.coremvvm.utils.Error
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
                var throwable: Throwable? = null
                withContext(Dispatchers.IO) {
                    try {
                        loginUseCase.execute(email, password)
                    } catch (e: Exception) {
                        throwable = e
                    }
                }
                if (throwable != null) {
                    error.value = throwable
                    uiState.value = Error
                } else {
                    uiState.value = HasData
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
            validateEmail(email) -> {
                emailValid.value = false
                valid = false
            }
            else -> emailValid.value = true
        }
        when {
            password.isBlank() -> {
                passwordValid.value = false
                valid = false
            }
            else -> passwordValid.value = true
        }
        return valid
    }

    private fun validateEmail(email: String): Boolean {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()
    }

}