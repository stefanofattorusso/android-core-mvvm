package com.stefattorusso.coremvvm.ui.home.view

import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.models.MenuModel
import com.stefattorusso.coremvvm.ui.home.adapter.HomeAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel(), HomeAdapter.AdapterCallback {

    var modelList: MutableLiveData<List<MenuModel>> = MutableLiveData()
    var selectedItem: MutableLiveData<MenuModel> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onItemClicked(position: Int) {
        selectedItem.value = (modelList.value as List<MenuModel>)[position]
    }

    override fun onCreated() {
        loadData()
    }

    private fun loadData() {
        launchAction {
            loading.value = true
            modelList.value = withContext(Dispatchers.Default) { init() }
            loading.value = false
        }
    }

    companion object {

        // HOME ITEMS
        internal const val LIST = "LIST"

        private fun init(): List<MenuModel> {
            val sampleItemList = mutableListOf<MenuModel>()
            sampleItemList.add(MenuModel(0, LIST, "A sample list view"))
            return sampleItemList
        }
    }
}