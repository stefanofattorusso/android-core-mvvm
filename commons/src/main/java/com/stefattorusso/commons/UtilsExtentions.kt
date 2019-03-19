package com.stefattorusso.commons

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object UtilsExtentions

fun Context.isNetworkAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}