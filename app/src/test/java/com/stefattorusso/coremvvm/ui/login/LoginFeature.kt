package com.stefattorusso.coremvvm.ui.login

import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseUser
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.inOrder
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.ui.login.view.LoginViewModel
import com.stefattorusso.coremvvm.utils.Error
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.UIState
import com.stefattorusso.domain.interactor.LoginUseCase
import com.stefattorusso.domain.interactor.impl.LoginUseCaseImpl
import com.stefattorusso.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginFeature: BaseTestShould() {

    @Mock
    private lateinit var stateLiveDataObserver: Observer<UIState>
    @Mock
    private lateinit var authRepository: AuthRepository
//    @Mock
//    private lateinit var loginUseCase: LoginUseCase
    @Mock
    private lateinit var loggedInUserResponse: FirebaseUser

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var loginViewModel: LoginViewModel
    private val enableLoading = Loading
    private val loginResults = HasData
    private val errorResults = Error

    @Before
    fun initialize() {
        loginUseCase = LoginUseCaseImpl(authRepository)
        loginViewModel = LoginViewModel().also {
            it.loginUseCase = loginUseCase
        }
        loginViewModel.uiState.value = null
    }

    @Test
    fun perform_login() = runBlocking {
        given(authRepository.loginWithEmailAndPassword("test@test.com", "test")).willReturn(loggedInUserResponse)

        loginViewModel.uiState.observeForever(stateLiveDataObserver)
        loginViewModel.loginUserWithEmail("test@test.com", "test")

        inOrder(stateLiveDataObserver) {
            verify(stateLiveDataObserver).onChanged(enableLoading)
            verify(stateLiveDataObserver).onChanged(loginResults)
//            verify(stateLiveDataObserver).onChanged(errorResults)
        }
    }
}