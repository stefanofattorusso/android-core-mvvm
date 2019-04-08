package com.stefattorusso.coremvvm.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.stefattorusso.commons.lifecyclehelpers.autoinflate.AutoInflateHelper
import com.stefattorusso.commons.lifecyclehelpers.autoinflate.AutoInflateHelperCallback
import com.stefattorusso.coremvvm.helpers.NavigationHelper
import com.stefattorusso.coremvvm.utils.ErrorHandler
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector, CoroutineScope,
    AutoInflateHelperCallback {

    @Inject
    lateinit var mExceptionHandler: ErrorHandler
    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var mAutoInflateHelper: AutoInflateHelper
    @Inject
    lateinit var mNavigationHelper: NavigationHelper

    private var mContentView: View? = null
    private val mJob: Job by lazy { Job() }

    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAutoInflateHelper.setSavedInstanceState(savedInstanceState)
    }

    override fun onDestroy() {
        mJob.cancel()
        super.onDestroy()
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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mFragmentInjector

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    protected fun launchAction(block: suspend CoroutineScope.() -> Unit) {
        launch(mExceptionHandler, block = block)
    }
}