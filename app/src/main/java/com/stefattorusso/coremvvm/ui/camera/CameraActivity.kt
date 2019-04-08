package com.stefattorusso.coremvvm.ui.camera

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.newInstance
import com.stefattorusso.commons.permission.getCameraPermission
import com.stefattorusso.coremvvm.base.BaseActivity
import com.stefattorusso.coremvvm.ui.camera.view.CameraFragment
import com.stefattorusso.coremvvm.utils.ImagePickerUtil
import kotlinx.android.synthetic.main.grid_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CameraActivity : BaseActivity(), InjectFragmentHelperCallback<CameraFragment>, CameraFragment.FragmentCallback {

    @Inject
    lateinit var mInjectFragmentHelperImpl: InjectFragmentHelper

    private var mMainFragment: CameraFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mInjectFragmentHelperImpl.setSavedInstanceState(savedInstanceState)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            retrieveImageFromResult(resultCode, data)
        }
    }

    // Fragment Helper

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = content

    override fun onCreateFragment(): CameraFragment = CameraFragment().newInstance(intent.extras)

    override fun onFragmentLoaded(fragment: CameraFragment) {
        mMainFragment = fragment
    }

    override fun onTakePictureClicked() {
        launchAction {
            val hasPermission: Boolean = withContext(Dispatchers.Default) {
                getCameraPermission()
            }
            if (hasPermission) {
                handlePickTakePicture()
            }
        }
    }

    private fun handlePickTakePicture() {
        launchAction {
            val intent = withContext(Dispatchers.IO) {
                ImagePickerUtil.getPickImageIntent(this@CameraActivity, "Choose an option")
            }
            if (intent?.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun retrieveImageFromResult(resultCode: Int, resultData: Intent?) {
        launchAction {
            val uri = withContext(Dispatchers.IO) {
                ImagePickerUtil.getUriFromResult(this@CameraActivity, resultCode, resultData)
            }
            if (uri != null) {
                mMainFragment?.drawPicture(uri)
            }
        }

    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 10023
    }
}
