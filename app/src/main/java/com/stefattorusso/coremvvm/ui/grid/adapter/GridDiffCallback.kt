package com.stefattorusso.coremvvm.ui.grid.adapter

import com.stefattorusso.commons.BaseDiffCallback
import com.stefattorusso.coremvvm.data.models.ImageModel

class GridDiffCallback(oldItems: List<ImageModel>, newItems: List<ImageModel>) :
    BaseDiffCallback<ImageModel>(oldItems, newItems) {

    override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
        return oldItem == newItem
    }
}