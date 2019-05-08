package com.stefattorusso.coremvvm.ui.grid.view

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.mapper.ImageModelMapper
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.NoData
import com.stefattorusso.domain.Image
import com.stefattorusso.domain.interactor.GetImageListUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GridViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var mImageModelMapper: ImageModelMapper
    @Inject
    lateinit var getImageListUseCase: GetImageListUseCase

    private var imageList = listOf<Image>()
    private var imageModelList: MutableLiveData<List<ImageModel>> = MutableLiveData()
    private var selectedItem: MutableLiveData<Pair<ImageView, Image>> = MutableLiveData()

    override fun onCreated() {
        super.onCreated()
        loadData()
    }

    fun onItemClicked(view: ImageView, position: Int) {
        selectedItem.value = Pair(view, imageList[position])
    }

    fun getImageModelList(): LiveData<List<ImageModel>> = imageModelList

    fun getSelectedItem(): LiveData<Pair<ImageView, Image>> = selectedItem

    private fun loadData() {
        launch {
            uiState.value = Loading
            imageModelList.value = withContext(dispatcher.background) {
                imageList = retrieveList()
                mapObjects(imageList)
            }
            uiState.value = NoData.takeIf { imageList.isNullOrEmpty() } ?: HasData
        }
    }

    private suspend fun retrieveList(): List<Image> {
        return getImageListUseCase.execute().shuffled()
    }

    private fun mapObjects(list: List<Image>): List<ImageModel> {
        return list.map { mImageModelMapper.transform(it) }
    }
}