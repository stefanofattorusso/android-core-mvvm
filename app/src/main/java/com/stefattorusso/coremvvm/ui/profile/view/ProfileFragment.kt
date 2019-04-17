package com.stefattorusso.coremvvm.ui.profile.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.ProfileFragmentBinding

class ProfileFragment : BaseFragment<ProfileFragment.FragmentCallback, ProfileViewModel, ProfileFragmentBinding>() {

    interface FragmentCallback : BaseFragmentCallback

    override val mViewModelClass: Class<ProfileViewModel>
        get() = ProfileViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel
        mViewModel.uiState.observe(viewLifecycleOwner, Observer {

        })
    }
}