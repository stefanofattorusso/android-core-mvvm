package com.stefattorusso.commons.lifecyclehelpers.autoinflate

import android.os.Bundle
import android.view.View


interface AutoInflateHelperCallback {
    fun onContentViewCreated(view: View, savedInstanceState: Bundle?)
}