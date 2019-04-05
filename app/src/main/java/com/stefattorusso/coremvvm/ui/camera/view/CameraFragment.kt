package com.stefattorusso.coremvvm.ui.camera.view

import android.os.Bundle
import android.view.View
import com.stefattorusso.commons.permission.getCameraPermission
import com.stefattorusso.coremvvm.base.BaseFragment
import com.stefattorusso.coremvvm.databinding.CameraFragmentBinding
import com.stefattorusso.coremvvm.utils.ImagePickerUtil
import kotlinx.android.synthetic.main.camera_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CameraFragment : BaseFragment<CameraFragment.FragmentCallback, CameraViewModel, CameraFragmentBinding>() {

    interface FragmentCallback : BaseFragment.BaseFragmentCallback {
        fun onTakePictureClicked()
    }

    override val mViewModelClass: Class<CameraViewModel>
        get() = CameraViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeData()
    }

    private fun setupViews() {
        mViewDataBinding?.viewModel = mViewModel
        take_picture_image.setOnClickListener {
            mCallback.onTakePictureClicked()
        }
    }

    private fun observeData() {

    }

    private fun checkCameraPermission(): Boolean{
        var hasPermission = false
        launchAction {
            withContext(Dispatchers.Default){
                hasPermission = activity?.getCameraPermission() ?: false
            }
        }
        return hasPermission
    }

    private fun handlePickTakePicture(){
        launchAction {
            val intent = ImagePickerUtil.getPickImageIntent(context!!, "Choose an option")
        }
    }
}