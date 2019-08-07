package com.stefattorusso.coremvvm.ui.home

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.inOrder
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel
import com.stefattorusso.coremvvm.utils.Error
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.UIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeFeature : BaseTestShould() {

    @Mock
    private lateinit var stateLiveDataObserver: Observer<UIState>

    private lateinit var homeViewModel: HomeViewModel
    private val enableLoading = Loading
    private val homeResults = HasData
    private val errorResults = Error

    @Before
    fun initialize() {
        homeViewModel = HomeViewModel()
    }

    @Test
    fun perform_load_data() = coroutinesTestRule.testDispatcher.runBlockingTest {
        homeViewModel.uiState.observeForever(stateLiveDataObserver)
        homeViewModel.loadData()

        inOrder(stateLiveDataObserver) {
            verify(stateLiveDataObserver).onChanged(enableLoading)
            verify(stateLiveDataObserver).onChanged(homeResults)
        }
    }
}