package com.stefattorusso.coremvvm.ui.home.adapter

import com.stefattorusso.commons.BaseDiffCallback
import com.stefattorusso.coremvvm.data.models.MenuModel

class HomeDiffCallback(oldItems: List<MenuModel>, newItems: List<MenuModel>) :
    BaseDiffCallback<MenuModel>(oldItems, newItems) {

    override fun areItemsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MenuModel, newItem: MenuModel): Boolean {
        return oldItem == newItem
    }
}