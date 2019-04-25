package com.stefattorusso.coremvvm.utils

object ValidatorHelper {

    fun validateEmail(email: String): Boolean {
        return Constants.VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()
    }
}