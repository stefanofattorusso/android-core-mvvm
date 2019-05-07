package com.stefattorusso.coremvvm.data.mapper

import android.content.Context
import com.stefattorusso.coremvvm.R
import com.stefattorusso.coremvvm.data.models.ImageModel
import com.stefattorusso.domain.Image
import javax.inject.Inject

open class ImageModelMapper @Inject constructor(
    private val context: Context
) : Mapper<ImageModel, Image> {

    override fun transform(source: Image) =
        ImageModel(
            source.id,
            source.width,
            source.height,
            source.format,
            source.author,
            source.postUrl,
            source.filename,
            source.authorUrl,
            context.getString(R.string.api_domain) + "400/600/?image=${source.id}",
            source.like
        )
}