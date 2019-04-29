package com.stefattorusso.coremvvm.ui.login

import com.google.firebase.auth.FirebaseUser
import com.nhaarman.mockitokotlin2.given
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.domain.interactor.LoginUseCase
import com.stefattorusso.domain.interactor.impl.LoginUseCaseImpl
import com.stefattorusso.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseShould : BaseTestShould() {

    @Mock
    lateinit var authRepository: AuthRepository
    @Mock
    lateinit var loggedUser: FirebaseUser

    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun init() {
        loginUseCase = LoginUseCaseImpl(authRepository)
    }

    @Test
    fun return_successful_result_when_login_succeed() = runBlocking {
        given(authRepository.loginWithEmailAndPassword("test@test.com", "test")).willReturn(loggedUser)
        val result = loginUseCase.execute("test@test.com", "test")
        assertEquals(result, loggedUser)
    }

    @Test
    fun return_failure_result_when_login_fail() = runBlocking {
        given(authRepository.loginWithEmailAndPassword("test@test.com", "test")).willReturn(null)
        val result = loginUseCase.execute("test@test.com", "test")
        assertNull(result)
    }
}