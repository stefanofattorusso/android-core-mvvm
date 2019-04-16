package com.stefattorusso.data.entity.mapper

import com.stefattorusso.data.entity.ImageEntity
import com.stefattorusso.domain.Image


fun ImageEntity.transform() = Image(
    id ?: 0,
    width ?: 0,
    height ?: 0,
    format ?: "",
    author ?: "",
    postUrl ?: "",
    filename ?: "",
    authorUrl ?: "",
    false
)
