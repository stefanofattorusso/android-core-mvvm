package com.stefattorusso.commons

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    protected var mData = mutableListOf<T>()

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewItemHolder(holder, getItem(position), position)
    }

    abstract fun onBindViewItemHolder(holder: VH, data: T, position: Int)

    override fun getItemCount() = mData.size

    fun getItem(position: Int) = mData[position]

    fun getItemPosition(data: T) = mData.indexOf(data)

    open fun setItems(data: List<T>, diffResult: DiffUtil.DiffResult) {
        mData = data.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItem(data: T) {
        mData.add(data)
        notifyItemInserted(getItemPosition(data))
    }

    fun addItem(data: T, position: Int) {
        var position = position
        position = if (position == -1) itemCount else position
        mData.add(position, data)
        notifyItemInserted(position)
    }

    fun addItems(data: List<T>) {
        val position = mData.size
        mData.addAll(data)
        notifyItemRangeInserted(position, data.size)
    }

    fun addItems(data: List<T>, position: Int) {
        mData.addAll(position, data)
        notifyItemRangeInserted(position, data.size)
    }

    fun removeItem(data: T) {
        removeItem(getItemPosition(data))
    }

    fun removeItem(position: Int) {
        if (position < itemCount) {
            mData.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun moveItem(data: T, toPosition: Int) {
        moveItem(getItemPosition(data), toPosition)
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(mData, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun updateItem(data: T) {
        updateItem(mData.indexOf(data))
    }

    fun updateItem(position: Int) {
        notifyItemChanged(position)
    }

    fun clearItems() {
        mData = ArrayList()
        notifyDataSetChanged()
    }

}