package com.stefattorusso.domain

import com.nhaarman.mockitokotlin2.given
import com.stefattorusso.domain.interactor.GetRandomImageListUseCase
import com.stefattorusso.domain.interactor.impl.GetRandomImageListUseCaseImpl
import com.stefattorusso.domain.repository.ImageRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRandomImageListUseCaseShould : BaseTestShould() {

    @Mock
    lateinit var imageRepository: ImageRepository

    private lateinit var getRandomImageListUseCase: GetRandomImageListUseCase

    private val results = Outcome.Success(listOf(Image()))
    private val emptyResults = Outcome.Success(emptyList<Image>())
    private val networkError = Outcome.Error(Outcome.FailureReason.NETWORK_ERROR, Exception())
    private val unknownError = Outcome.Error(Outcome.FailureReason.UNKNOWN_ERROR, Exception())

    @Before
    fun init() {
        getRandomImageListUseCase = GetRandomImageListUseCaseImpl(imageRepository)
    }

    @Test
    fun return_successful_result_when_has_data() = runBlocking {
        given(imageRepository.retrieveList()).willReturn(results)
        val result = getRandomImageListUseCase.execute()
        assertEquals(result, results)
    }

    @Test
    fun return_successful_result_when_has_no_data() = runBlocking {
        given(imageRepository.retrieveList()).willReturn(emptyResults)
        val result = getRandomImageListUseCase.execute()
        assertEquals(result, emptyResults)
    }

    @Test
    fun return_failure_result_when_fail_to_load_data() = runBlocking {
        given(imageRepository.retrieveList()).willReturn(null)
        val result = getRandomImageListUseCase.execute()
        assertNull(result)
    }

    @Test
    fun return_failure_result_caused_by_network_error() = runBlocking {
        given(imageRepository.retrieveList()).willReturn(networkError)
        val result = getRandomImageListUseCase.execute()
        assertEquals(result, networkError)
    }

    @Test
    fun return_failure_result_caused_by_unknown_error() = runBlocking {
        given(imageRepository.retrieveList()).willReturn(unknownError)
        val result = getRandomImageListUseCase.execute()
        assertEquals(result, unknownError)
    }
}