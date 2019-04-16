package com.stefattorusso.coremvvm.ui.splash.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.SplashFragmentBinding
import com.stefattorusso.coremvvm.utils.HasData
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseFragment<SplashFragment.FragmentCallback, SplashViewModel, SplashFragmentBinding>() {

    interface FragmentCallback : BaseFragment.BaseFragmentCallback {
        fun onLoginClicked()
        fun onSignupClicked()
        fun onShowHome()
    }

    override val mViewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel
        skip_button.setOnClickListener { mCallback.onShowHome() }
        login_button.setOnClickListener { mCallback.onLoginClicked() }
        signup_button.setOnClickListener { mCallback.onSignupClicked() }
        mViewModel.uiState.observe(viewLifecycleOwner, Observer {
            if (it == HasData) mCallback.onShowHome()
        })
    }
}