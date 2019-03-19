package com.stefattorusso.data.entity

import com.squareup.moshi.Json

data class ImageEntity(
    @Json(name = "id") var id: Int? = null,
    @Json(name = "width") var width: Int? = null,
    @Json(name = "height") var height: Int? = null,
    @Json(name = "format") var format: String? = null,
    @Json(name = "author") var author: String? = null,
    @Json(name = "post_url") var postUrl: String? = null,
    @Json(name = "filename") var filename: String? = null,
    @Json(name = "author_url") var authorUrl: String? = null
)
