package com.stefattorusso.coremvvm.ui.detail.view

import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.mapper.transform
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.domain.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {
    var selectedItemModel: MutableLiveData<ImageModel> = MutableLiveData()

    fun setImageItem(image: Image) {
        launchAction {
            var imageModel: ImageModel? = null
            withContext(Dispatchers.IO) {
                imageModel = image.transform()
            }
            selectedItemModel.value = imageModel
        }
    }
}