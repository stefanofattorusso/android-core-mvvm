package com.stefattorusso.coremvvm.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.stefattorusso.commons.lifecyclehelpers.autoinflate.AutoInflateHelper
import com.stefattorusso.commons.lifecyclehelpers.autoinflate.AutoInflateHelperCallback
import com.stefattorusso.coremvvm.helpers.NavigationHelper
import com.stefattorusso.coremvvm.utils.ErrorHandler
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), AutoInflateHelperCallback {

    @Inject
    lateinit var mExceptionHandler: ErrorHandler
    @Inject
    lateinit var mAutoInflateHelper: AutoInflateHelper
    @Inject
    lateinit var mNavigationHelper: NavigationHelper

    private var mContentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAutoInflateHelper.setSavedInstanceState(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

}