package com.stefattorusso.coremvvm.ui.profile.view

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.stefattorusso.coremvvm.base.mvvm.BaseVMFragment
import com.stefattorusso.coremvvm.databinding.ProfileFragmentBinding

class ProfileFragment : BaseVMFragment<ProfileFragment.FragmentCallback, ProfileViewModel, ProfileFragmentBinding>() {

    interface FragmentCallback : BaseVMFragmentCallback

    override val viewModelClass: Class<ProfileViewModel>
        get() = ProfileViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: ProfileViewModel) {
        viewModel.uiState.observe(viewLifecycleOwner, Observer {})
    }
}