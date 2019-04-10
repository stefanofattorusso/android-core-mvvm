package com.stefattorusso.coremvvm.ui.login.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.LoginFragmentBinding
import com.stefattorusso.coremvvm.utils.HasData

class LoginFragment : BaseFragment<LoginFragment.FragmentCallback, LoginViewModel, LoginFragmentBinding>() {

    interface FragmentCallback : BaseFragment.BaseFragmentCallback {
        fun onLoginSuccess()
    }

    override val mViewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel
        mViewModel.uiState.observe(viewLifecycleOwner, Observer {
            if (it == HasData) {
                mCallback.onLoginSuccess()
            }
        })
    }
}