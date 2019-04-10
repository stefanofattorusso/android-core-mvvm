package com.stefattorusso.coremvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stefattorusso.commons.lifecyclehelpers.autoinflateview.AutoInflateViewHelper
import com.stefattorusso.commons.lifecyclehelpers.autoinflateview.AutoInflateViewHelperCallback
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.base.mvvm.ViewModelFactory
import com.stefattorusso.coremvvm.utils.ErrorHandler
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment<TCallback : BaseFragment.BaseFragmentCallback, VM : BaseViewModel, VDB : ViewDataBinding> :
    Fragment(), HasSupportFragmentInjector, AutoInflateViewHelperCallback {

    @Inject
    lateinit var mExceptionHandler: ErrorHandler
    @Inject
    lateinit var mChildFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    @Inject
    lateinit var mAutoInflateHelper: AutoInflateViewHelper
    @Inject
    lateinit var mCallback: TCallback

    interface BaseFragmentCallback

    lateinit var mViewModel: VM
    protected abstract val mViewModelClass: Class<VM>
    protected var mViewDataBinding: VDB? = null
    private var mView: View? = null

    override fun onViewInflated(view: View) {
        mView = view
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = DataBindingUtil.bind(view)
        mViewDataBinding?.lifecycleOwner = this
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(mViewModelClass).also { it.onCreated() }
        mViewModel.getError().observe(viewLifecycleOwner, Observer {
            mExceptionHandler.handleException(it)
        })
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mChildFragmentInjector
}