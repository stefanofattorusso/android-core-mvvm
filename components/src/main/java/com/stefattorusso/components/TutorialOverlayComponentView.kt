package com.stefattorusso.components

import android.content.Context
import android.graphics.*
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import kotlinx.android.parcel.Parcelize

class TutorialOverlayComponentView : View {

    private lateinit var circlePaint: Paint
    private lateinit var rectPaint: Paint
    private lateinit var rect: RectF

    private var currentStep: TutorialStep? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init()
    }

    private fun init() {
        circlePaint = Paint().apply {
            color = Color.TRANSPARENT
            xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT) as Xfermode?
        }
        rectPaint = Paint().apply {
            color = Color.parseColor("#99000000")
        }
        rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(rect, rectPaint)

        currentStep?.let {
            canvas?.drawCircle(it.x, it.y, 100f, circlePaint)
        }
    }

    override fun isInEditMode(): Boolean {
        return true
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        rect.set(0f, 0f, width.toFloat(), height.toFloat())
    }

    fun setStep(step: TutorialStep) {
        currentStep = step
        invalidate()
    }

    @Parcelize
    data class TutorialStep(var x: Float, var y: Float, var text: String) : Parcelable
}