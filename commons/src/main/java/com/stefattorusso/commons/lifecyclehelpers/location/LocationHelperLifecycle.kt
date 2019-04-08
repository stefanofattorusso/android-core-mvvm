package com.stefattorusso.commons.lifecyclehelpers.location

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.stefattorusso.commons.AppCoroutineScope
import com.stefattorusso.commons.permission.getLocationPermission
import kotlinx.coroutines.launch

class LocationHelperLifecycle(
    private val activity: FragmentActivity
) : LifecycleObserver {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        activity.lifecycle.addObserver(this)
    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        AppCoroutineScope.launch {
            if (activity.getLocationPermission()) {
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    (activity as LocationHelperCallback).onLastLocationRetrieved(it)
                }
            }
        }
    }
}