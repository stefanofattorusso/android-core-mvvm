package com.stefattorusso.coremvvm.ui.camera

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.stefattorusso.commons.newInstance
import com.stefattorusso.commons.permission.getCameraPermission
import com.stefattorusso.coremvvm.base.BaseFragmentActivity
import com.stefattorusso.coremvvm.ui.camera.view.CameraFragment
import com.stefattorusso.coremvvm.utils.ImagePickerUtil
import kotlinx.android.synthetic.main.grid_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CameraActivity : BaseFragmentActivity<CameraFragment>(), CameraFragment.FragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onLoadFragmentContainer(): View = content

    override fun onCreateFragment(): CameraFragment = CameraFragment().newInstance(intent.extras)

    override fun onTakePictureClicked() {
        lifecycleScope.launch {
            val hasPermission: Boolean = withContext(Dispatchers.Default) { getCameraPermission() }
            if (hasPermission) {
                handlePickTakePicture()
            }
        }
    }

    private fun handlePickTakePicture() {
        lifecycleScope.launch {
            val intent = withContext(Dispatchers.IO) {
                ImagePickerUtil.getPickImageIntent(this@CameraActivity, "Choose an option")
            }
            if (intent?.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun retrieveImageFromResult(resultCode: Int, resultData: Intent?) {
        lifecycleScope.launch {
            val uri = withContext(Dispatchers.IO) {
                ImagePickerUtil.getUriFromResult(this@CameraActivity, resultCode, resultData)
            }
            if (uri != null) {
                getFragment()?.drawPicture(uri)
            }
        }

    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 10023
    }
}
