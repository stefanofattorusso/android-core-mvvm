package com.stefattorusso.coremvvm.ui.grid

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verifyBlocking
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.ui.grid.view.GridViewModel
import com.stefattorusso.coremvvm.utils.TestCoroutineDispatchersImpl
import com.stefattorusso.coremvvm.utils.coroutines.CoroutineDispatchers
import com.stefattorusso.domain.Image
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

    private val list = listOf(Image())
    private val emptyList = emptyList<Image>()

    @Before
    fun initialize() {
        coroutineDispatchers = TestCoroutineDispatchersImpl()
        gridViewModel = GridViewModel().also {
            it.getImageListUseCase = getImageListUseCase
            it.dispatcher = coroutineDispatchers
            it.mImageModelMapper = imageModelMapper
        }
    }

    @Test
    fun return_successful_data_result() = runBlocking {
        given(getImageListUseCase.execute()).willReturn(list)

        gridViewModel.onCreated()

        verifyBlocking(imageModelMapper) { transform(list[0]) }
    }

    @Test
    fun return_successful_data_empty_result() = runBlocking {
        given(getImageListUseCase.execute()).willReturn(emptyList)

        gridViewModel.onCreated()

        verifyBlocking(imageModelMapper, never()) { transform(list[0]) }
    }

    @Test
    fun return_failure_data_no_result() = runBlocking {
        given(getImageListUseCase.execute()).willReturn(null)

        gridViewModel.onCreated()

        verifyBlocking(imageModelMapper, never()) { transform(list[0]) }
    }
}