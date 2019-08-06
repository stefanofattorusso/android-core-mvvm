package com.stefattorusso.coremvvm.ui.home

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.inOrder
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel
import com.stefattorusso.coremvvm.utils.*
import com.stefattorusso.data.coroutines.CoroutineDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeFeature : BaseTestShould() {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var stateLiveDataObserver: Observer<UIState>

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var coroutineDispatchers: CoroutineDispatchers
    private val enableLoading = Loading
    private val homeResults = HasData
    private val errorResults = Error

    @Before
    fun initialize() {
        coroutineDispatchers = TestCoroutineDispatchersImpl()
        homeViewModel = HomeViewModel()
    }

    @Test
    fun perform_load_data() = coroutinesTestRule.testDispatcher.runBlockingTest {
        homeViewModel.uiState.observeForever(stateLiveDataObserver)

        inOrder(stateLiveDataObserver) {
            verify(stateLiveDataObserver).onChanged(enableLoading)
            verify(stateLiveDataObserver).onChanged(homeResults)
        }
    }
}