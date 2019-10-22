package com.stefattorusso.coremvvm.ui.grid

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.inOrder
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.ui.grid.view.GridViewModel
import com.stefattorusso.coremvvm.utils.*
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.interactor.GetRandomImageListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GridFeature : BaseTestShould() {

    @Mock
    private lateinit var stateLiveDataObserver: Observer<UIState>
    @Mock
    private lateinit var getRandomImageListUseCase: GetRandomImageListUseCase
    @Mock
    private lateinit var imageModelMapper: ImageModelMapper

    private lateinit var gridViewModel: GridViewModel
    private val enableLoading = Loading
    private val homeResults = HasData
    private val emptyResults = NoData
    private val errorResults = Error
    private val list = Outcome.Success(listOf(Image()))
    private val emptyList = Outcome.Success(emptyList<Image>())

    @Before
    fun initialize() {
        gridViewModel = GridViewModel(getRandomImageListUseCase, imageModelMapper)
    }

    @Test
    fun perform_load_data() = coroutinesTestRule.testDispatcher.runBlockingTest {
        given(getRandomImageListUseCase.execute()).willReturn(list)
        gridViewModel.uiState.observeForever(stateLiveDataObserver)
        gridViewModel.loadData()

        inOrder(stateLiveDataObserver) {
            verify(stateLiveDataObserver).onChanged(enableLoading)
            verify(stateLiveDataObserver).onChanged(homeResults)
        }
    }

    @Test
    fun perform_load_empty_data() = coroutinesTestRule.testDispatcher.runBlockingTest {
        given(getRandomImageListUseCase.execute()).willReturn(emptyList)
        gridViewModel.uiState.observeForever(stateLiveDataObserver)
        gridViewModel.loadData()

        inOrder(stateLiveDataObserver) {
            verify(stateLiveDataObserver).onChanged(enableLoading)
            verify(stateLiveDataObserver).onChanged(emptyResults)
        }
    }
}