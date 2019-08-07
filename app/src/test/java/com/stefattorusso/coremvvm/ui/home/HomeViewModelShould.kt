package com.stefattorusso.coremvvm.ui.home

import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.data.models.MenuModel
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel
import com.stefattorusso.coremvvm.utils.TestCoroutineDispatchersImpl
import com.stefattorusso.data.coroutines.CoroutineDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelShould : BaseTestShould() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var coroutineDispatchers: CoroutineDispatchers

    private val resultData = listOf(MenuModel(0, HomeViewModel.LIST, "A sample list view"))
    private val results: ArrayList<MenuModel>? = null
    private val emptyData = emptyList<MenuModel>()

    @Before
    fun initialize() {
        coroutineDispatchers = TestCoroutineDispatchersImpl()
        homeViewModel = HomeViewModel()
    }

    @Test
    fun return_successful_data_result() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val result = homeViewModel.initData()
        assertTrue(result.isNotEmpty())
    }
}