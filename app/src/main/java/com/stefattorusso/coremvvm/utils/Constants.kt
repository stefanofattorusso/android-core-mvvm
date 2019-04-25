package com.stefattorusso.coremvvm.utils

import java.util.regex.Pattern

object Constants {

    val VALID_EMAIL_ADDRESS_REGEX: Pattern = Pattern.compile(
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE
    )
}