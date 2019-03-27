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
        internal const val VIEW = "VIEW"
        internal const val LIST = "LIST"
        internal const val HORIZONTAL_LIST = "HORIZONTAL_LIST"
        internal const val PAGER = "PAGER"
        internal const val MULTIPLE_FRAGMENTS = "MULTIPLE_FRAGMENTS"
        internal const val LOCATION = "LOCATION"
        internal const val PICTURE = "PICTURE"
        internal const val LOCALE = "LOCALE"
        internal const val VIBRATION = "VIBRATION"
        internal const val INPUT = "INPUT"
        internal const val SOCIAL = "SOCIAL"
        internal const val NOTICE_DIALOG = "NOTICE_DIALOG"
        internal const val BOTTOM_NAV = "BOTTOM_NAV"
        internal const val AUTH_PHONE = "AUTH_PHONE"
        internal const val COROUTINE = "COROUTINE"

        private const val NUM_ITEMS = 14

        private val sampleItemList = ArrayList<MenuModel>(NUM_ITEMS)

        private fun init() {
            if (sampleItemList.isEmpty()) {
                sampleItemList.add(MenuModel(0, LIST, "A sample list view"))
                sampleItemList.add(MenuModel(1, VIEW, "A sample view"))
                sampleItemList.add(MenuModel(2, HORIZONTAL_LIST, "A sample list view with nested horizontal list"))
                sampleItemList.add(MenuModel(3, PAGER, "A sample pager view"))
                sampleItemList.add(MenuModel(4, MULTIPLE_FRAGMENTS, "A sample view with multiple fragments"))
                sampleItemList.add(MenuModel(5, LOCATION, "A sample view to show device location"))
                sampleItemList.add(MenuModel(6, PICTURE, "A sample view to take or pick up photos"))
                sampleItemList.add(MenuModel(7, LOCALE, "A sample view to change device language"))
                sampleItemList.add(MenuModel(8, VIBRATION, "A sample view to use device vibration"))
                sampleItemList.add(MenuModel(9, INPUT, "A view with input layouts"))
                sampleItemList.add(MenuModel(10, SOCIAL, "A view with socials connection"))
                sampleItemList.add(MenuModel(11, NOTICE_DIALOG, "A view with Notice Dialog"))
                sampleItemList.add(MenuModel(12, BOTTOM_NAV, "A view with a bottom bar navigation"))
                sampleItemList.add(MenuModel(13, AUTH_PHONE, "A view with check phone number validation"))
                sampleItemList.add(MenuModel(14, COROUTINE, "A view with coroutine samples"))
            }
        }
    }
}