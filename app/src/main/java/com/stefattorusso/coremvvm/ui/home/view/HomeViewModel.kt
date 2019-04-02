package com.stefattorusso.coremvvm.ui.home.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.models.MenuModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel()/*, HomeAdapter.AdapterCallback*/ {

    private var modelList: MutableLiveData<List<MenuModel>> = MutableLiveData()
    private var selectedItem: MutableLiveData<MenuModel> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

/*    override fun onItemClicked(position: Int) {
        selectedItem.value = (modelList.value as List<MenuModel>)[position]
    }*/

    override fun onCreated() {
        loadData()
    }

    fun getSelectedItem(): LiveData<MenuModel> = selectedItem

    fun getLoading(): LiveData<Boolean> = loading

    fun getModelList(): LiveData<List<MenuModel>> = modelList

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