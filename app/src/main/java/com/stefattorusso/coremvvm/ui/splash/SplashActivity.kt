package com.stefattorusso.coremvvm.ui.splash

import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.fullscreen.FullScreenActivityLifecycle
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.splash.view.SplashFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class SplashActivity : BaseActivity(), InjectFragmentHelperCallback<SplashFragment>,
    SplashFragment.FragmentCallback{

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper
    @Inject
    lateinit var mFullScreenActivityLifecycle: FullScreenActivityLifecycle

    private var mFragment: SplashFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): SplashFragment = SplashFragment().newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: SplashFragment) {
        mFragment = fragment
    }

    override fun onLoginClicked() {
        mNavigationHelper.launchLoginView()
    }

    override fun onSignupClicked() {

    }

    override fun onShowHome() {
        mNavigationHelper.launchHomeAndFinishCurrent()
    }
}
