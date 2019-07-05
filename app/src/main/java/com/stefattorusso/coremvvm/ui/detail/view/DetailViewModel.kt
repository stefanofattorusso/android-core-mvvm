package com.stefattorusso.coremvvm.ui.detail.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.domain.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var mImageModelMapper: ImageModelMapper

    private var selectedItem: Image? = null
    private var selectedItemModel: MutableLiveData<ImageModel> = MutableLiveData()
    private var imageLoaded: MutableLiveData<Boolean> = MutableLiveData()

    fun setImageItem(image: Image) {
        selectedItem = image
        transformModel(image)
    }

    fun startPostponedTransition() {
        imageLoaded.value = true
    }

    fun getSelectedItemModel(): LiveData<ImageModel> = selectedItemModel

    fun getImageLoaded(): LiveData<Boolean> = imageLoaded

    fun likeIt() {
        selectedItem?.like = !(selectedItem?.like ?: false)
        transformModel(selectedItem)
    }

    private fun transformModel(image: Image?) {
        if (image != null) {
            viewModelScope.launch {
                selectedItemModel.value = withContext(Dispatchers.Default) {
                    mImageModelMapper.transform(image)
                }
            }
        }
    }
}