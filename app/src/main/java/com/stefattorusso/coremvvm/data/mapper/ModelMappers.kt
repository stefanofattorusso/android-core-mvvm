package com.stefattorusso.coremvvm.data.mapper

import android.content.Context
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.domain.Image
import javax.inject.Inject

class ModelMappers @Inject constructor(
    private val context: Context
) {
    fun transform(image: Image) = ImageModel(
        image.id,
        image.width,
        image.height,
        image.format,
        image.author,
        image.postUrl,
        image.filename,
        image.authorUrl,
        context.getString(R.string.api_domain) + "400/600/?image=${image.id}"
    )
}