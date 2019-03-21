package com.stefattorusso.commons.helpers.injectfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


interface InjectFragmentHelperCallback<T : Fragment> {

    fun onLoadFragmentContainer(savedInstanceState: Bundle?): View

    fun onCreateFragment(): T

    fun onFragmentLoaded(fragment: T)
}