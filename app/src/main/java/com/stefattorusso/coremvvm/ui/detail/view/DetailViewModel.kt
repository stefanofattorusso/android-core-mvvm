package com.stefattorusso.coremvvm.ui.detail.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.domain.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var mImageModelMapper: ImageModelMapper

    private var selectedItemModel: MutableLiveData<ImageModel> = MutableLiveData()
    private var imageLoaded: MutableLiveData<Boolean> = MutableLiveData()

    fun setImageItem(image: Image) {
        launchAction {
            selectedItemModel.value = withContext(Dispatchers.IO) {
                mImageModelMapper.transform(image)
            }
        }
    }

    fun startPostponedTransition(){
        imageLoaded.value = true
    }

    fun getSelectedItemModel(): LiveData<ImageModel> = selectedItemModel

    fun getImageLoaded(): LiveData<Boolean> = imageLoaded
}