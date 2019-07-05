package com.stefattorusso.coremvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.stefattorusso.commons.lifecyclehelpers.autoinflateview.AutoInflateViewHelper
import com.stefattorusso.commons.lifecyclehelpers.autoinflateview.AutoInflateViewHelperCallback
import com.stefattorusso.coremvvm.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment<VDB : ViewDataBinding, TCallback : BaseFragment.BaseFragmentCallback> :
    Fragment(), HasSupportFragmentInjector, AutoInflateViewHelperCallback {

    @Inject
    lateinit var mChildFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var mAutoInflateHelper: AutoInflateViewHelper
    @Inject
    lateinit var mCallback: TCallback

    interface BaseFragmentCallback

    private lateinit var mViewDataBinding: VDB

    private var mView: View? = null

    private var mProgressView: View? = null

    override fun onViewInflated(view: View) {
        mView = view
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProgressView = view.findViewById(R.id.progress_view)
        DataBindingUtil.bind<VDB>(view)?.let { binding ->
            mViewDataBinding = binding
            mViewDataBinding.lifecycleOwner = viewLifecycleOwner
            onBindingCreated(binding)
        }
    }

    abstract fun onBindingCreated(binding: VDB)

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mChildFragmentInjector

    protected open fun showProgress() {
        mProgressView?.visibility = View.VISIBLE
    }

    protected open fun hideProgress() {
        mProgressView?.visibility = View.GONE
    }

    protected fun showError(exception: Throwable?) {
        showError(exception?.localizedMessage)
    }

    protected fun showError(errorMessage: String?) {
        val dialog = AlertDialog.Builder(context!!)
            .setTitle("Doh!")
            .setMessage(errorMessage)
            .setPositiveButton("close", null)
            .create()
        dialog.show()
    }
}