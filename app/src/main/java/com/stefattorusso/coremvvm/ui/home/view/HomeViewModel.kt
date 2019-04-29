package com.stefattorusso.coremvvm.ui.home.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.models.MenuModel
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.NoData
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    private var modelList: MutableLiveData<List<MenuModel>> = MutableLiveData()
    private var selectedItem: MutableLiveData<MenuModel> = MutableLiveData()

    override fun onCreated() {
        super.onCreated()
        loadData()
    }

    fun getSelectedItem(): LiveData<MenuModel> = selectedItem

    fun getModelList(): LiveData<List<MenuModel>> = modelList

    fun onItemClicked(position: Int) {
        selectedItem.value = modelList.value?.get(position)
    }

    fun initData(): List<MenuModel> {
        val sampleItemList = mutableListOf<MenuModel>()
        sampleItemList.add(MenuModel(0, LIST, "A sample list view"))
        sampleItemList.add(MenuModel(1, CAMERA, "A sample camera view"))
        sampleItemList.add(MenuModel(2, LOCATION, "A sample location view"))
        sampleItemList.add(MenuModel(3, LOGIN, "A sample login view"))
        return sampleItemList
    }

    private fun loadData() {
        launchAction {
            uiState.value = Loading
            modelList.value = withContext(coroutineDispatcher.background) { initData() }
            uiState.value = NoData.takeIf { modelList.value.isNullOrEmpty() } ?: HasData
        }
    }

    companion object {

        // HOME ITEMS
        internal const val LIST = "LIST"
        internal const val CAMERA = "CAMERA"
        internal const val LOCATION = "LOCATION"
        internal const val LOGIN = "LOGIN"
    }
}