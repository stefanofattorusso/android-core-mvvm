package com.stefattorusso.coremvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stefattorusso.coremvvm.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseTestShould {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

}