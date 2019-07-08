package com.stefattorusso.coremvvm.ui.splash

import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.fullscreen.FullScreenActivityLifecycle
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.splash.view.SplashFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashFragment>(), SplashFragment.FragmentCallback {

    @Inject
    lateinit var mFullScreenActivityLifecycle: FullScreenActivityLifecycle

    // Fragment Helper

    override fun onLoadFragmentContainer(): View = content

    override fun onCreateFragment(): SplashFragment = SplashFragment().newInstance(intent.extras)

    override fun onLoginClicked() {
        mNavigationHelper.launchLoginView()
    }

    override fun onSignupClicked() {

    }

    override fun onShowHome() {
        mNavigationHelper.launchHomeAndFinishCurrent()
    }
}
