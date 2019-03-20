package com.stefattorusso.coremvvm.data.mapper

import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.domain.Image


fun Image.transform() = ImageModel(
    id,
    width,
    height,
    format,
    author,
    postUrl,
    filename,
    authorUrl
)
