package com.stefattorusso.commons.permission

import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CompletableDeferred

class PermissionsFragment : Fragment() {

    private val deferredGrant = CompletableDeferred<Boolean>()
    suspend fun awaitGrant() = deferredGrant.await()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        val code = arguments?.getInt("CODE")
        if (code != null) {
            checkPermissionsToLaunch(code)
        } else {
            exit()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_STORAGE_CODE,
            PERMISSION_CAMERA_CODE -> {
                if (grantResults[0] == PERMISSION_GRANTED) {
                    deferredGrant.complete(true)
                    exit()
                    return
                } else if (shouldShowRequestPermissionRationale(permissions[0])) {
                    // We insist
                    return
                }
                deferredGrant.complete(false)
                exit()
            }
        }
    }

    private fun checkPermissionsToLaunch(code: Int) {
        when (code) {
            PERMISSION_STORAGE_CODE -> {
                if (ContextCompat.checkSelfPermission(context!!, READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(READ_EXTERNAL_STORAGE), code)
                } else {
                    deferredGrant.complete(true)
                    exit()
                }
            }
            PERMISSION_CAMERA_CODE -> {
                if (ContextCompat.checkSelfPermission(context!!, CAMERA) != PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), code)
                } else {
                    deferredGrant.complete(true)
                    exit()
                }
            }
        }
    }

    private fun exit() {
        retainInstance = false
        activity?.run {
            if (!isFinishing) supportFragmentManager
                .beginTransaction()
                .remove(this@PermissionsFragment)
                .commitAllowingStateLoss()
        }
    }

    companion object {
        internal const val PERMISSION_STORAGE_CODE = 100
        internal const val PERMISSION_CAMERA_CODE = 101
    }
}

suspend fun FragmentActivity.getStoragePermission(): Boolean {
    return launchFragment(PermissionsFragment.PERMISSION_STORAGE_CODE)
}

suspend fun FragmentActivity.getCameraPermission(): Boolean {
    return launchFragment(PermissionsFragment.PERMISSION_CAMERA_CODE)
}

private suspend fun FragmentActivity.launchFragment(code: Int): Boolean {
    val fragment = PermissionsFragment().apply {
        arguments = Bundle().apply { putInt("CODE", code) }
    }
    if (!isFinishing) supportFragmentManager
        .beginTransaction()
        .add(fragment, null)
        .commitAllowingStateLoss()
    return fragment.awaitGrant()
}