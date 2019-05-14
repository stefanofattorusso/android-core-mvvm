package com.stefattorusso.coremvvm.ui.home

import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.newInstance
import com.stefattorusso.components.TutorialOverlayComponentView
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.home.view.HomeFragment
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.CAMERA
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.LIST
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.LOCATION
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.LOGIN
import com.stefattorusso.coremvvm.ui.home.view.HomeViewModel.Companion.PROFILE
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), InjectFragmentHelperCallback<HomeFragment>, HomeFragment.FragmentCallback {

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper

    private var mHomeFragment: HomeFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): HomeFragment = HomeFragment().newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: HomeFragment) {
        mHomeFragment = fragment
    }

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
