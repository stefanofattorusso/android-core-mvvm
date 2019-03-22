package com.stefattorusso.coremvvm.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stefattorusso.commons.BaseRecyclerAdapter
import com.stefattorusso.components.ImageComponentView
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.data.models.ImageModel

class MainAdapter : BaseRecyclerAdapter<ImageModel, MainAdapter.MainViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return mData[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ImageComponentView(parent.context))
    }

    override fun onBindViewItemHolder(holder: MainViewHolder, data: ImageModel, position: Int) {
        holder.bindData(data)
    }

    inner class MainViewHolder(private val mItemView: ImageComponentView) : RecyclerView.ViewHolder(mItemView) {

        fun bindData(data: ImageModel) {
            mItemView.setImage(mItemView.context.getString(R.string.api_domain) + "200/300/?image=" + data.id)
        }
    }
}