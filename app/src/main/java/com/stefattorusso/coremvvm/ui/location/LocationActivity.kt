package com.stefattorusso.coremvvm.ui.location

import android.location.Location
import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.lifecyclehelpers.location.LocationHelperCallback
import com.stefattorusso.commons.lifecyclehelpers.location.LocationHelperLifecycle
import com.stefattorusso.commons.newInstance
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.location.view.LocationFragment
import kotlinx.android.synthetic.main.grid_activity.*
import javax.inject.Inject

class LocationActivity : BaseActivity(), InjectFragmentHelperCallback<LocationFragment>,
    LocationFragment.FragmentCallback,
    LocationHelperCallback {

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper
    @Inject
    lateinit var mLocationHelperLifecycle: LocationHelperLifecycle

    private var mFragment: LocationFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): LocationFragment = LocationFragment().newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: LocationFragment) {
        mFragment = fragment
    }

    override fun onLastLocationRetrieved(location: Location) {
        mFragment?.drawLocation(location)
    }
}
