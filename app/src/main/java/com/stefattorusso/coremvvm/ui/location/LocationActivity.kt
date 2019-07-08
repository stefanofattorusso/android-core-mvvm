package com.stefattorusso.coremvvm.ui.location

import android.location.Location
import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.location.LocationHelperCallback
import com.stefattorusso.commons.lifecyclehelpers.location.LocationHelperLifecycle
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.location.view.LocationFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class LocationActivity : BaseActivity<LocationFragment>(), LocationFragment.FragmentCallback,
    LocationHelperCallback {

    @Inject
    lateinit var mLocationHelperLifecycle: LocationHelperLifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(): View = content

    override fun onCreateFragment(): LocationFragment = LocationFragment().newInstance(intent.extras)

    override fun onLastLocationRetrieved(location: Location) {
        getFragment()?.drawLocation(location)
    }
}
