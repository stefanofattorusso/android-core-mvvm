package com.stefattorusso.coremvvm.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.stefattorusso.commons.getFragment
import com.stefattorusso.commons.loadFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragmentActivity<TFragment> : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    private var mFragment: TFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createFragment(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mFragmentInjector

    protected fun getFragment() = mFragment

    protected abstract fun onLoadFragmentContainer(): View

    protected abstract fun onCreateFragment(): TFragment

    private fun createFragment(savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            val contentView = onLoadFragmentContainer()
            if (savedInstanceState == null) {
                mFragment = onCreateFragment()
                mFragment?.let { loadFragment(contentView.id, it as Fragment) }
            } else {
                mFragment = getFragment(contentView.id) as? TFragment
            }
        }
    }
}