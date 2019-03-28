package com.stefattorusso.coremvvm.ui.home.view

import androidx.lifecycle.MutableLiveData
import com.stefattorusso.coremvvm.base.mvvm.BaseViewModel
import com.stefattorusso.coremvvm.data.models.MenuModel
import com.stefattorusso.coremvvm.ui.home.adapter.HomeAdapter
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel(), HomeAdapter.AdapterCallback {

    init {
        init()
    }

    var modelList: MutableLiveData<List<MenuModel>> = MutableLiveData()
    var selectedItem: MutableLiveData<MenuModel> = MutableLiveData()

    override fun onItemClicked(position: Int) {
        selectedItem.value = sampleItemList[position]
    }

    fun loadData() {
        launchAction {
            modelList.value = sampleItemList
        }
    }

    companion object {

        // HOME ITEMS
        internal const val LIST = "LIST"

        private const val NUM_ITEMS = 1

        private val sampleItemList = ArrayList<MenuModel>(NUM_ITEMS)

        private fun init() {
            if (sampleItemList.isEmpty()) {
                sampleItemList.add(MenuModel(0, LIST, "A sample list view"))
            }
        }
    }
}