package com.stefattorusso.coremvvm.ui.detail

import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.fullscreen.FullScreenActivityLifecycle
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.detail.view.DetailFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), InjectFragmentHelperCallback<DetailFragment>, DetailFragment.FragmentCallback {

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper
    @Inject
    lateinit var mFullScreenActivityLifecycle: FullScreenActivityLifecycle

    private var mMainFragment: DetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): DetailFragment = DetailFragment().newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: DetailFragment) {
        mMainFragment = fragment
    }

    override fun onAnimationEnd() {
        finishAfterTransition()
    }
}
