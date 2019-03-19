package com.stefattorusso.coremvvm.base

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.stefattorusso.coremvvm.base.mvvm.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding> : Fragment(), HasSupportFragmentInjector,
    Injectable {

    @Inject
    lateinit var mChildFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    protected abstract val mViewModelClass: Class<VM>
    protected val mViewModel: VM by lazy { ViewModelProvider(this, mViewModelFactory).get(mViewModelClass) }
    protected var mViewDataBinding: VDB? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = DataBindingUtil.bind(view)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mChildFragmentInjector
}