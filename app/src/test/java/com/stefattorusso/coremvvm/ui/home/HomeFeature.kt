package com.stefattorusso.coremvvm.ui.home

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.inOrder
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel
import com.stefattorusso.coremvvm.utils.*
import com.stefattorusso.coremvvm.utils.coroutines.CoroutineDispatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeFeature : BaseTestShould(){

    @Mock
    private lateinit var stateLiveDataObserver: Observer<UIState>

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var coroutineDispatchers: CoroutineDispatchers
    private val enableLoading = Loading
    private val homeResults = HasData
    private val errorResults = Error

    @Before
    fun initialize(){
        coroutineDispatchers = TestCoroutineDispatchersImpl()
        homeViewModel = HomeViewModel().also {
            it.dispatcher = coroutineDispatchers
            it.uiState.value = null
        }
    }

    @Test
    fun perform_load_data(){
        homeViewModel.uiState.observeForever(stateLiveDataObserver)
        homeViewModel.onCreated()

        inOrder(stateLiveDataObserver) {
            verify(stateLiveDataObserver).onChanged(enableLoading)
            verify(stateLiveDataObserver).onChanged(homeResults)
        }
    }
}