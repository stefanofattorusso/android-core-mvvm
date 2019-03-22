package com.stefattorusso.commons.lifecyclehelpers.autoinflate

import android.os.Bundle

interface AutoInflateHelper {
    fun setSavedInstanceState(savedInstanceState: Bundle?)
    fun setLayoutId(layoutId: Int)
}