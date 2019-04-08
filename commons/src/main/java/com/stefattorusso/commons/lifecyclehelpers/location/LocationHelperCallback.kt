package com.stefattorusso.commons.lifecyclehelpers.location

import android.location.Location


interface LocationHelperCallback {
    fun onLastLocationRetrieved(location: Location)
}