package com.stefattorusso.coremvvm.ui.grid

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verifyBlocking
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.ui.grid.view.GridViewModel
import com.stefattorusso.coremvvm.utils.TestCoroutineDispatchersImpl
import com.stefattorusso.data.coroutines.CoroutineDispatchers
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.Outcome
import com.stefattorusso.domain.interactor.GetImageListUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GridViewModelShould : BaseTestShould() {

    @Mock
    private lateinit var getImageListUseCase: GetImageListUseCase
    @Mock
    private lateinit var imageModelMapper: ImageModelMapper

    private lateinit var coroutineDispatchers: CoroutineDispatchers
    private lateinit var gridViewModel: GridViewModel

    private val list = Outcome.Success(listOf(Image()))
    private val emptyList = Outcome.Success(emptyList<Image>())

    @Before
    fun initialize() {
        coroutineDispatchers = TestCoroutineDispatchersImpl()
        gridViewModel = GridViewModel(getImageListUseCase, imageModelMapper)
    }

    @Test
    fun return_successful_data_result() = runBlocking {
        given(getImageListUseCase.execute()).willReturn(list)

        gridViewModel.onAttached()

        verifyBlocking(imageModelMapper) { transform(list.value[0]) }
    }

    @Test
    fun return_successful_data_empty_result() = runBlocking {
        given(getImageListUseCase.execute()).willReturn(emptyList)

        gridViewModel.onAttached()

        verifyBlocking(imageModelMapper, never()) { transform(list.value[0]) }
    }

    @Test
    fun return_failure_data_no_result() = runBlocking {
        given(getImageListUseCase.execute()).willReturn(null)

        gridViewModel.onAttached()

        verifyBlocking(imageModelMapper, never()) { transform(list.value[0]) }
    }
}