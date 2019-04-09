package com.stefattorusso.coremvvm.ui.login.view

import android.os.Bundle
import android.view.View
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.LoginFragmentBinding

class LoginFragment : BaseFragment<LoginFragment.FragmentCallback, LoginViewModel, LoginFragmentBinding>() {

    interface FragmentCallback : BaseFragment.BaseFragmentCallback

    override val mViewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel
    }
}