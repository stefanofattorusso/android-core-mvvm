package com.stefattorusso.coremvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.stefattorusso.commons.BaseRecyclerAdapter
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.coremvvm.databinding.RowImageViewBinding

class MainAdapter(
    private val callback: AdapterCallback
) : BaseRecyclerAdapter<ImageModel, MainAdapter.MainViewHolder>() {

    interface AdapterCallback {
        fun onItemClicked(view: ImageView, position: Int)
    }

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return mData[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_image_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewItemHolder(holder: MainViewHolder, data: ImageModel, position: Int) {
        holder.bindData(data)
    }

    inner class MainViewHolder(
        private val binding: RowImageViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { callback.onItemClicked(binding.imageView, adapterPosition) }
        }

        fun bindData(data: ImageModel) {
            binding.imageModel = data
            binding.executePendingBindings()
        }
    }
}