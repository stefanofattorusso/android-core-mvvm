package com.stefattorusso.coremvvm.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.main.view.MainFragment
import com.stefattorusso.domain.Image
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : BaseActivity(), InjectFragmentHelperCallback<MainFragment>, MainFragment.FragmentCallback {

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper

    private var mMainFragment: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): MainFragment = MainFragment.newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: MainFragment) {
        mMainFragment = fragment
    }

    // Fragment Callbacks

    override fun onShowDetail(view: ImageView, image: Image) {
        mNavigationHelper.launchDetailWithTransactionAnimation(view, image)
    }
}
