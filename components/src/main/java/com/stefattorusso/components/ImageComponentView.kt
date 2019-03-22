package com.stefattorusso.components

import android.content.Context
import android.util.AttributeSet
import com.stefattorusso.commons.loadUrl
import kotlinx.android.synthetic.main.image_component_view.view.*

class ImageComponentView : BaseComponentView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun getLayoutId() = R.layout.image_component_view

    override fun loadAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {

    }

    override fun bindViews() {

    }

    override fun loadData() {

    }

    fun setImage(url: String){
        image_view.loadUrl(url, 200, 200)
    }
}