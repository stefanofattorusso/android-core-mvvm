package com.stefattorusso.coremvvm.ui.main.view

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.stefattorusso.coremvvm.base.BaseViewModel
import com.stefattorusso.coremvvm.data.mapper.transform
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.coremvvm.ui.main.adapter.MainDiffCallback
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.interactor.GetImageListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var getImageListUseCase: GetImageListUseCase

    var imageList = listOf<Image>()
    var imageModelList: MutableLiveData<Pair<List<ImageModel>, DiffUtil.DiffResult>> = MutableLiveData()

    fun loadData() {
        displayLoader(true)
        launchAction {
            imageList = withContext(Dispatchers.IO) {
                getImageListUseCase.execute()
            }

            imageModelList.value = withContext(Dispatchers.IO) {
                calculateDiff(imageList.map { it.transform() })
            }
        }
    }

    fun displayLoader(show: Boolean) {
        loader.value = show
    }

    private suspend fun calculateDiff(items: List<ImageModel>): Pair<List<ImageModel>, DiffUtil.DiffResult> {
        val callback = MainDiffCallback(imageModelList.value?.first ?: emptyList(), items)
        val result = DiffUtil.calculateDiff(callback, true)
        return Pair(items, result)
    }
}