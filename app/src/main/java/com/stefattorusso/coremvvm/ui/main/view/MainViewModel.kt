package com.stefattorusso.coremvvm.ui.main.view

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.mapper.transform
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.coremvvm.ui.main.adapter.MainAdapter
import com.stefattorusso.coremvvm.ui.main.adapter.MainDiffCallback
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.interactor.GetImageListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel(), MainAdapter.AdapterCallback {

    @Inject
    lateinit var getImageListUseCase: GetImageListUseCase

    private var imageList = listOf<Image>()
    var imageModelList: MutableLiveData<Pair<List<ImageModel>, DiffUtil.DiffResult>> = MutableLiveData()
    var selectedItem: MutableLiveData<Pair<ImageView, Image>> = MutableLiveData()

    override fun onItemClicked(view: ImageView, position: Int) {
        selectedItem.value = Pair(view, imageList[position])
    }

    fun loadData() {
        launchAction {
            imageList = withContext(Dispatchers.IO) {
                getImageListUseCase.execute()
            }

            imageModelList.value = withContext(Dispatchers.IO) {
                calculateDiff(imageList.map { it.transform() })
            }
        }
    }

    private fun calculateDiff(items: List<ImageModel>): Pair<List<ImageModel>, DiffUtil.DiffResult> {
        val callback = MainDiffCallback(imageModelList.value?.first ?: emptyList(), items)
        val result = DiffUtil.calculateDiff(callback, true)
        return Pair(items, result)
    }
}