package com.stefattorusso.coremvvm.ui.splash.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.stefattorusso.coremvvm.base.mvvm.BaseVMFragment
import com.stefattorusso.coremvvm.databinding.SplashFragmentBinding
import com.stefattorusso.coremvvm.utils.HasData
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseVMFragment<SplashFragment.FragmentCallback, SplashViewModel, SplashFragmentBinding>() {

    interface FragmentCallback : BaseVMFragmentCallback {
        fun onLoginClicked()
        fun onSignupClicked()
        fun onShowHome()
    }

    override val viewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: SplashViewModel) {
        viewModel.uiState.observe(viewLifecycleOwner, Observer { if (it == HasData) mCallback.onShowHome() })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        skip_button.setOnClickListener { mCallback.onShowHome() }
        login_button.setOnClickListener { mCallback.onLoginClicked() }
        signup_button.setOnClickListener { mCallback.onSignupClicked() }
    }
}