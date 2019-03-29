package com.stefattorusso.coremvvm.ui.detail.view

import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.domain.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {
    var selectedItemModel: MutableLiveData<ImageModel> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var imageLoaded: MutableLiveData<Boolean> = MutableLiveData()

    fun setImageItem(image: Image) {
        launchAction {
            loading.value = true
            selectedItemModel.value = withContext(Dispatchers.IO) {
                mModelMappers.transform(image)
            }
            loading.value = false
        }
    }

    fun startPostponedTransition(){
        imageLoaded.value = true
    }
}