package com.stefattorusso.coremvvm.ui.detail.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.domain.Image
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var mImageModelMapper: ImageModelMapper

    private var selectedItem: MutableLiveData<Image> = MutableLiveData()
    private var imageLoaded: MutableLiveData<Boolean> = MutableLiveData()

    fun setImageItem(image: Image) {
        selectedItem.value = image
    }

    fun startPostponedTransition() {
        imageLoaded.value = true
    }

    fun getSelectedItemModel(): LiveData<ImageModel> = Transformations.map(selectedItem){
        mImageModelMapper.transform(it)
    }

    fun getImageLoaded(): LiveData<Boolean> = imageLoaded

    fun likeIt() {
        selectedItem.value?.like = !(selectedItem.value?.like ?: false)
    }
}