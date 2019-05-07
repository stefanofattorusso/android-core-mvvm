package com.stefattorusso.coremvvm.ui.login

import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.inOrder
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.ui.login.view.LoginViewModel
import com.stefattorusso.coremvvm.utils.*
import com.stefattorusso.coremvvm.utils.coroutines.CoroutineDispatchers
import com.stefattorusso.data.repository.AuthRepositoryImpl
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
class LoginFeature : BaseTestShould() {

    @Mock
    private lateinit var stateLiveDataObserver: Observer<UIState>
    @Mock
    private lateinit var firebaseAuth: FirebaseAuth
    @Mock
    private lateinit var loggedInResult: Task<AuthResult>

    private lateinit var coroutineDispatchers: CoroutineDispatchers
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var authRepository: AuthRepository
    private val enableLoading = Loading
    private val loginResults = HasData
    private val errorResults = Error

    @Before
    fun initialize() {
        coroutineDispatchers = TestCoroutineDispatchersImpl()
        authRepository = AuthRepositoryImpl(firebaseAuth)
        loginUseCase = LoginUseCaseImpl(authRepository)
        loginViewModel = LoginViewModel().also {
            it.loginUseCase = loginUseCase
            it.dispatcher = coroutineDispatchers
            it.uiState.value = null
        }
    }

    @Test
    fun perform_login() = runBlocking {
        given(firebaseAuth.signInWithEmailAndPassword("test@test.com", "test")).willReturn(loggedInResult)

        loginViewModel.uiState.observeForever(stateLiveDataObserver)
        loginViewModel.loginUserWithEmail("test@test.com", "test")

        inOrder(stateLiveDataObserver) {
            verify(stateLiveDataObserver).onChanged(enableLoading)
            verify(stateLiveDataObserver).onChanged(loginResults)
        }
    }
}