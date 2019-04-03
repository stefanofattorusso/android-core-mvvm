package com.stefattorusso.coremvvm.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class BaseListAdapter<T, VM> : ListAdapter<T, BaseListAdapter.BaseViewHolder<T, VM>> {

    private var mViewModel: VM

    constructor(viewModel: VM, diffCallback: DiffUtil.ItemCallback<T>) : super(diffCallback) {
        this.mViewModel = viewModel
    }

    constructor(viewModel: VM) : super(object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }
    }) {
        this.mViewModel = viewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VM> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        return BaseViewHolder(mViewModel, binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, VM>, position: Int) = holder.bindData(getItem(position))

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    abstract fun getLayoutIdForPosition(position: Int): Int

    open fun setItems(newData: List<T>?) {
        submitList(newData)
    }

    class BaseViewHolder<T, VM>(
        private val viewModel: VM,
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: T) {
            binding.setVariable(BR.model, data)
            binding.setVariable(BR.position, adapterPosition)
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }
    }
}