package com.stefattorusso.coremvvm.ui.location.view

import android.location.Location
import androidx.lifecycle.LifecycleOwner
import com.stefattorusso.coremvvm.base.mvvm.BaseVMFragment
import com.stefattorusso.coremvvm.databinding.LocationFragmentBinding
import kotlinx.android.synthetic.main.location_fragment.*

class LocationFragment : BaseVMFragment<LocationFragment.FragmentCallback, LocationViewModel, LocationFragmentBinding>() {

    interface FragmentCallback : BaseVMFragmentCallback

    override val viewModelClass: Class<LocationViewModel>
        get() = LocationViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: LocationViewModel) {

    }

    fun drawLocation(location: Location) {
        location_text.text = "Latitude: ${location.latitude} \nLongitude: ${location.longitude}"
    }
}