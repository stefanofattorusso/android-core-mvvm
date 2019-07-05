package com.stefattorusso.coremvvm.ui.grid

import android.content.Context
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.inOrder
import com.stefattorusso.coremvvm.BaseTestShould
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.ui.grid.view.GridViewModel
import com.stefattorusso.coremvvm.utils.*
import com.stefattorusso.coremvvm.utils.coroutines.CoroutineDispatchers
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.interactor.GetImageListUseCase
import com.stefattorusso.domain.interactor.impl.GetImageListUseCaseImpl
import com.stefattorusso.domain.repository.ImageRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GridFeature : BaseTestShould() {

    @Mock
    private lateinit var stateLiveDataObserver: Observer<UIState>
    @Mock
    private lateinit var imageRepository: ImageRepository
    @Mock
    private lateinit var context: Context

    private lateinit var gridViewModel: GridViewModel
    private lateinit var coroutineDispatchers: CoroutineDispatchers
    private lateinit var getImageListUseCase: GetImageListUseCase
    private lateinit var imageModelMapper: ImageModelMapper
    private val enableLoading = Loading
    private val homeResults = HasData
    private val emptyResults = NoData
    private val errorResults = Error
    private val list = listOf(Image())
    private val emptyList = emptyList<Image>()

    @Before
    fun initialize() {
        imageModelMapper = ImageModelMapper(context)
        getImageListUseCase = GetImageListUseCaseImpl(imageRepository)
        coroutineDispatchers = TestCoroutineDispatchersImpl()
        gridViewModel = GridViewModel().also {
            it.dispatcher = coroutineDispatchers
            it.getImageListUseCase = getImageListUseCase
            it.mImageModelMapper = imageModelMapper
            it.uiState.value = null
        }
    }

    @Test
    fun perform_load_data() = runBlocking {
        given(imageRepository.retrieveList()).willReturn(list)
        gridViewModel.uiState.observeForever(stateLiveDataObserver)
        gridViewModel.onAttached()

        inOrder(stateLiveDataObserver) {
            verify(stateLiveDataObserver).onChanged(enableLoading)
            verify(stateLiveDataObserver).onChanged(homeResults)
        }
    }

    @Test
    fun perform_load_empty_data() = runBlocking {
        given(imageRepository.retrieveList()).willReturn(emptyList)
        gridViewModel.uiState.observeForever(stateLiveDataObserver)
        gridViewModel.onAttached()

        inOrder(stateLiveDataObserver) {
            verify(stateLiveDataObserver).onChanged(enableLoading)
            verify(stateLiveDataObserver).onChanged(emptyResults)
        }
    }
}