package com.stefattorusso.coremvvm.ui.camera.view

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.loadUri
import com.stefattorusso.commons.setOnClick
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.CameraFragmentBinding
import kotlinx.android.synthetic.main.camera_fragment.*

class CameraFragment : BaseFragment<CameraFragment.FragmentCallback, CameraViewModel, CameraFragmentBinding>() {

    interface FragmentCallback : BaseFragmentCallback {
        fun onTakePictureClicked()
    }

    override val mViewModelClass: Class<CameraViewModel>
        get() = CameraViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeData()
    }

    fun drawPicture(uri: Uri) {
        take_picture_image.loadUri(uri)
    }

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel
        take_picture_image.setOnClick {
            mCallback.onTakePictureClicked()
        }
    }

    private fun observeData() {

    }
}