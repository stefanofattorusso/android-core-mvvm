package com.stefattorusso.coremvvm.ui.login.view

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.stefattorusso.coremvvm.base.mvvm.BaseVMFragment
import com.stefattorusso.coremvvm.databinding.LoginFragmentBinding
import com.stefattorusso.coremvvm.utils.HasData
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseVMFragment<LoginFragment.FragmentCallback, LoginViewModel, LoginFragmentBinding>() {

    interface FragmentCallback : BaseVMFragmentCallback {
        fun onLoginSuccess()
    }

    override val viewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: LoginViewModel) {
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            if (it == HasData) mCallback.onLoginSuccess()
        })
        viewModel.isEmailValid().observe(viewLifecycleOwner, Observer {
            if (it){
                username_input.error = null
            } else {
                username_input.error = "Insert a valid email address!"
            }
        })
        viewModel.isPasswordValid().observe(viewLifecycleOwner, Observer {
            if (it){
                password_input.error = null
            } else {
                password_input.error = "Password must not be empty!"
            }
        })
    }

}