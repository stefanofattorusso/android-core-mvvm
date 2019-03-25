package com.stefattorusso.coremvvm.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.stefattorusso.commons.lifecyclehelpers.autoinflate.AutoInflateHelper
import com.stefattorusso.commons.lifecyclehelpers.autoinflate.AutoInflateHelperCallback
import com.stefattorusso.coremvvm.helpers.NavigationHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector, AutoInflateHelperCallback {

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var mAutoInflateHelper: AutoInflateHelper
    @Inject
    lateinit var mNavigationHelper: NavigationHelper

    private var mContentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAutoInflateHelper.setSavedInstanceState(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mFragmentInjector

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }
}