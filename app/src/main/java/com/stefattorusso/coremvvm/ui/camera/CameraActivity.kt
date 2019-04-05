package com.stefattorusso.coremvvm.ui.camera

import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.camera.view.CameraFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class CameraActivity : BaseActivity(), InjectFragmentHelperCallback<CameraFragment>, CameraFragment.FragmentCallback {

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper

    private var mMainFragment: CameraFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): CameraFragment = CameraFragment().newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: CameraFragment) {
        mMainFragment = fragment
    }

    override fun onAnimationEnd() {
        finishAfterTransition()
    }
}
