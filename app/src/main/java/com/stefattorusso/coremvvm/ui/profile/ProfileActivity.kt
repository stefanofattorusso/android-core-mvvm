package com.stefattorusso.coremvvm.ui.profile

import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.profile.view.ProfileFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class ProfileActivity : BaseActivity(), InjectFragmentHelperCallback<ProfileFragment>,
    ProfileFragment.FragmentCallback{

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper

    private var mFragment: ProfileFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): ProfileFragment = ProfileFragment().newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: ProfileFragment) {
        mFragment = fragment
    }
}
