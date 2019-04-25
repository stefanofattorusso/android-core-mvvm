package com.stefattorusso.coremvvm.utils

import com.stefattorusso.coremvvm.BaseTestShould
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidatorHelperShould : BaseTestShould() {

    @Test
    fun return_true_for_valid_email() {
        assertTrue(ValidatorHelper.validateEmail("test@email.com"))
    }

    @Test
    fun return_false_for_invalid_email() {
        assertFalse(ValidatorHelper.validateEmail("test@email"))
    }
}