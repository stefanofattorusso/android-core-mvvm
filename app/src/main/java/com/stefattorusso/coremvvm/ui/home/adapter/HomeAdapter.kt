package com.stefattorusso.coremvvm.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.stefattorusso.commons.BaseRecyclerAdapter
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.data.models.MenuModel
import com.stefattorusso.coremvvm.databinding.RowHomeViewBinding

class HomeAdapter(
    private val callback: AdapterCallback
) : BaseRecyclerAdapter<MenuModel, HomeAdapter.HomeViewHolder>() {

    interface AdapterCallback {
        fun onItemClicked(position: Int)
    }

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return mData[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_home_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewItemHolder(holder: HomeViewHolder, data: MenuModel, position: Int) {
        holder.bindData(data)
    }

    inner class HomeViewHolder(
        private val binding: RowHomeViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonView.setOnClickListener { callback.onItemClicked(adapterPosition) }
        }

        fun bindData(data: MenuModel) {
            binding.menuModel = data
            binding.executePendingBindings()
        }
    }
}