package com.stefattorusso.coremvvm.ui.home

import android.view.View
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.components.TutorialOverlayComponentView
import com.stefattorusso.coremvvm.ui.home.view.HomeFragment
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.CAMERA
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.LIST
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.LOCATION
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.LOGIN
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.PROFILE
import kotlinx.android.synthetic.main.grid_activity.*

class HomeActivity : BaseActivity<HomeFragment>(), HomeFragment.FragmentCallback {

    // Fragment Helper

    override fun onLoadFragmentContainer(): View = content

    override fun onCreateFragment(): HomeFragment = HomeFragment().newInstance(intent.extras)

    // Fragment Callbacks

    override fun onMenuItemClicked(type: String) {
        when (type) {
            LIST -> mNavigationHelper.launchMain()
            CAMERA -> mNavigationHelper.launchCameraView()
            LOCATION -> mNavigationHelper.launchLocationView()
            LOGIN -> mNavigationHelper.launchLoginView()
            PROFILE -> mNavigationHelper.launchProfileView()
        }
    }

    override fun showTutorial(steps: List<TutorialOverlayComponentView.TutorialStep>) {
        mNavigationHelper.launchTutorial(steps)
    }
}
