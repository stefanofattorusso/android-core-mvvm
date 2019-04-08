package com.stefattorusso.coremvvm.ui.location.view

import android.location.Location
import android.os.Bundle
import android.view.View
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.LocationFragmentBinding
import kotlinx.android.synthetic.main.location_fragment.*

class LocationFragment : BaseFragment<LocationFragment.FragmentCallback, LocationViewModel, LocationFragmentBinding>() {

    interface FragmentCallback : BaseFragment.BaseFragmentCallback

    override val mViewModelClass: Class<LocationViewModel>
        get() = LocationViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    fun drawLocation(location: Location) {
        location_text.text = "Latitude: ${location.latitude} \nLongitude: ${location.longitude}"
    }

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel
    }
}