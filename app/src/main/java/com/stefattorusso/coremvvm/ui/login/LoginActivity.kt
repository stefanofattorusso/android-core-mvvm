package com.stefattorusso.coremvvm.ui.login

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.login.view.LoginFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), InjectFragmentHelperCallback<LoginFragment>,
    LoginFragment.FragmentCallback {

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper

    private var mFragment: LoginFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setHomeAsUpIndicator(R.drawable.ic_cancel_black_24dp)
        }
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): LoginFragment = LoginFragment().newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: LoginFragment) {
        mFragment = fragment
    }

    override fun onLoginSuccess() {
        mNavigationHelper.launchHomeAndFinishCurrent()
    }
}
