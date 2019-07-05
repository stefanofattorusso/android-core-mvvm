package com.stefattorusso.coremvvm.ui.camera.view

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.stefattorusso.commons.loadUri
import com.stefattorusso.commons.setOnClick
import com.stefattorusso.coremvvm.base.mvvm.BaseVMFragment
import com.stefattorusso.coremvvm.databinding.CameraFragmentBinding
import kotlinx.android.synthetic.main.camera_fragment.*

class CameraFragment : BaseVMFragment<CameraFragment.FragmentCallback, CameraViewModel, CameraFragmentBinding>() {

    interface FragmentCallback : BaseVMFragmentCallback {
        fun onTakePictureClicked()
    }

    override val viewModelClass: Class<CameraViewModel>
        get() = CameraViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: CameraViewModel) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    fun drawPicture(uri: Uri) {
        take_picture_image.loadUri(uri)
    }

    private fun setupViews() {
        take_picture_image.setOnClick {
            mCallback.onTakePictureClicked()
        }
    }
}