package com.stefattorusso.coremvvm.data.models

data class ImageModel(
    val id: Int,
    val width: Int,
    val height: Int,
    val format: String,
    val author: String,
    val postUrl: String,
    val filename: String,
    val authorUrl: String,
    val imageUrl: String
)
