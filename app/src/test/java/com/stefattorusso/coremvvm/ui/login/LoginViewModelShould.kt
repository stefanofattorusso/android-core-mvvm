package com.stefattorusso.coremvvm.ui.login

import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verifyBlocking
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.ui.login.view.LoginViewModel
import com.stefattorusso.coremvvm.utils.TestCoroutineDispatchersImpl
import com.stefattorusso.coremvvm.utils.coroutines.CoroutineDispatchers
import com.stefattorusso.domain.interactor.LoginUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelShould : BaseTestShould() {

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var coroutineDispatchers: CoroutineDispatchers

    @Before
    fun initialize() {
        coroutineDispatchers = TestCoroutineDispatchersImpl()
        loginViewModel = LoginViewModel().also {
            it.loginUseCase = loginUseCase
            it.coroutineDispatcher = coroutineDispatchers
        }
    }

    @Test
    fun perform_login() = runBlocking {
        loginViewModel.loginUserWithEmail("test@test.com", "test")

        verifyBlocking(loginUseCase) { execute("test@test.com", "test") }
    }

    @Test
    fun not_perform_login_with_invalid_data() = runBlocking {
        loginViewModel.loginUserWithEmail("", "")

        verifyBlocking(loginUseCase, never()) { execute("", "") }
    }
}