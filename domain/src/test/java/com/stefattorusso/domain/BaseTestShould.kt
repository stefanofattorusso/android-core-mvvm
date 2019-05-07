package com.stefattorusso.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseTestShould {

    @get:Rule
    val rule = InstantTaskExecutorRule()


}