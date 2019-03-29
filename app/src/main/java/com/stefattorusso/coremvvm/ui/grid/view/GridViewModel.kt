package com.stefattorusso.coremvvm.ui.grid.view

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.coremvvm.ui.grid.adapter.GridAdapter
import com.stefattorusso.coremvvm.ui.grid.adapter.GridDiffCallback
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.interactor.GetImageListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GridViewModel @Inject constructor() : BaseViewModel(), GridAdapter.AdapterCallback {

    @Inject
    lateinit var getImageListUseCase: GetImageListUseCase

    private var imageList = listOf<Image>()
    var imageModelList: MutableLiveData<Pair<List<ImageModel>, DiffUtil.DiffResult>> = MutableLiveData()
    var selectedItem: MutableLiveData<Pair<ImageView, Image>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onItemClicked(view: ImageView, position: Int) {
        selectedItem.value = Pair(view, imageList[position])
    }

    override fun onCreated() {
        loadData()
    }

    private fun loadData() {
        launchAction {
            loading.value = true
            imageList = withContext(Dispatchers.IO) {
                getImageListUseCase.execute().shuffled()
            }

            imageModelList.value = withContext(Dispatchers.IO) {
                calculateDiff(imageList.map { mModelMappers.transform(it) })
            }
            loading.value = false
        }
    }

    private fun calculateDiff(items: List<ImageModel>): Pair<List<ImageModel>, DiffUtil.DiffResult> {
        val callback = GridDiffCallback(imageModelList.value?.first ?: emptyList(), items)
        val result = DiffUtil.calculateDiff(callback, true)
        return Pair(items, result)
    }
}