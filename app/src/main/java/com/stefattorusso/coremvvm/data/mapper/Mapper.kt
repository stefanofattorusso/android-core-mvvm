package com.stefattorusso.coremvvm.data.mapper

interface Mapper<out V, in D> {

    fun transform(source: D): V
}