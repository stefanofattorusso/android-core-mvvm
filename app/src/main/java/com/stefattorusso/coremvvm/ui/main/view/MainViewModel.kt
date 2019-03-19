package com.stefattorusso.coremvvm.ui.main.view

import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.BaseViewModel
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.interactor.GetImageListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var getImageListUseCase: GetImageListUseCase

    var imageList: MutableLiveData<List<Image>> = MutableLiveData()

    fun loadData() {
        displayLoader(true)
        launchAction {
            imageList.value = withContext(Dispatchers.IO) {
                getImageListUseCase.execute()
            }
        }
    }

    fun displayLoader(show: Boolean) {
        loader.value = show

    }
}