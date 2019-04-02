package com.stefattorusso.coremvvm.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class BaseListAdapter<T> : ListAdapter<T, BaseListAdapter.BaseViewHolder<T>> {

    private var mData = listOf<T>()

    constructor(diffCallback: DiffUtil.ItemCallback<T>) : super(diffCallback)

    constructor() : super(object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getViewHolderLayoutId(),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(getItem(position))
//        onBindViewItemHolder(holder, position)
    }

//    abstract fun onBindViewItemHolder(holder: BaseViewHolder<T>, position: Int)

    abstract fun getViewHolderLayoutId(): Int

    open fun setItems(newData: List<T>?) {
        mData = newData ?: emptyList()
        submitList(mData)
    }

    fun getData(): List<T> = mData

    class BaseViewHolder<T>(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: T) {
            binding.setVariable(BR.model, data)
            binding.executePendingBindings()
        }
    }
}