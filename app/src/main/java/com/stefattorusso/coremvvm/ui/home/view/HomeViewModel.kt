package com.stefattorusso.coremvvm.ui.home.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.models.MenuModel
import com.stefattorusso.coremvvm.utils.HasData
import com.stefattorusso.coremvvm.utils.Loading
import com.stefattorusso.coremvvm.utils.NoData
import kotlinx.coroutines.Dispatchers
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

    private fun loadData() {
        launchAction {
            uiState.value = Loading
            modelList.value = withContext(Dispatchers.Default) { init() }
            uiState.value = NoData.takeIf { modelList.value.isNullOrEmpty() } ?: HasData
        }
    }

    companion object {

        // HOME ITEMS
        internal const val LIST = "LIST"
        internal const val CAMERA = "CAMERA"

        private fun init(): List<MenuModel> {
            val sampleItemList = mutableListOf<MenuModel>()
            sampleItemList.add(MenuModel(0, LIST, "A sample list view"))
            sampleItemList.add(MenuModel(1, CAMERA, "A sample camera view"))
            return sampleItemList
        }
    }
}