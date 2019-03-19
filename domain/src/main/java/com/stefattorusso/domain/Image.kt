package com.stefattorusso.domain

data class Image(
    val id: Int,
    val width: Int,
    val height: Int,
    val format: String,
    val author: String,
    val postUrl: String,
    val filename: String,
    val authorUrl: String
)