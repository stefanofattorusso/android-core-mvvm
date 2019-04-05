package com.stefattorusso.coremvvm.ui.camera.view

import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.domain.Image
import javax.inject.Inject

class CameraViewModel @Inject constructor() : BaseViewModel() {
//    private var selectedItemModel: MutableLiveData<ImageModel> = MutableLiveData()
//    private var imageLoaded: MutableLiveData<Boolean> = MutableLiveData()

    fun setImageItem(image: Image) {
//        launchAction {
//            selectedItemModel.value = withContext(Dispatchers.IO) {
//                mImageModelMapper.transform(image)
//            }
//        }
    }

    fun startPostponedTransition(){
//        imageLoaded.value = true
    }

//    fun getSelectedItemModel(): LiveData<ImageModel> = selectedItemModel

//    fun getImageLoaded(): LiveData<Boolean> = imageLoaded
}