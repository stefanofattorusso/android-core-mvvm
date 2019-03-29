package com.stefattorusso.commons

import androidx.recyclerview.widget.DiffUtil

interface BaseRecyclerAdapterCallback<T> {
    fun setItems(items: List<T>?)

    fun setItems(data: Pair<List<T>, DiffUtil.DiffResult>?)
}