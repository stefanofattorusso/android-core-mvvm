package com.stefattorusso.coremvvm.ui.main

import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.main.view.MainFragment
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : BaseActivity(), InjectFragmentHelperCallback<MainFragment> {

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper

    private var mMainFragment: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): MainFragment = MainFragment()

    override fun onFragmentLoaded(fragment: MainFragment) {
        mMainFragment = fragment
    }
}
