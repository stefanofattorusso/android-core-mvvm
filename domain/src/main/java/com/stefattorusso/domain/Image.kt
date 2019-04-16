package com.stefattorusso.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    var id: Int,
    var width: Int,
    var height: Int,
    var format: String,
    var author: String,
    var postUrl: String,
    var filename: String,
    var authorUrl: String,
    var like: Boolean
) : Parcelable