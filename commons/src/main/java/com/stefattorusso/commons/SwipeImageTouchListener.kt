package com.stefattorusso.commons

import android.view.MotionEvent
import android.view.View


class SwipeImageTouchListener(
    private val parentView: View,
    private val callback: () -> Unit
) : View.OnTouchListener {

    private var xCoOrdinate: Float = 0.toFloat()
    private var yCoOrdinate: Float = 0.toFloat()
    private var screenCenterX: Double = 0.toDouble()
    private var screenCenterY: Double = 0.toDouble()
    private var alpha: Int = 0
    private var maxHypo: Double = 0.toDouble()

    init {
        val display = parentView.context.resources.displayMetrics
        screenCenterX = (display.widthPixels / 2).toDouble()
        screenCenterY = (display.heightPixels - getStatusBarHeight()).toDouble() / 2
        maxHypo = Math.hypot(screenCenterX, screenCenterY)
    }

    override fun onTouch(v: View, event: MotionEvent?): Boolean {
        val centerYPos = v.y + v.height / 2
        val centerXPos = v.x + v.width / 2
        val a = screenCenterX - centerXPos
        val b = screenCenterY - centerYPos
        val hypo = Math.hypot(a, b)

        /**
         * change alpha of background of layout
         */
        alpha = (hypo * 255).toInt() / maxHypo.toInt()
        if (alpha < 255) parentView.background.alpha = 255 - alpha

        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                xCoOrdinate = v.x - event.rawX
                yCoOrdinate = v.y - event.rawY
            }
            MotionEvent.ACTION_MOVE -> v.animate()
                .x(event.rawX + xCoOrdinate)
                .y(event.rawY + yCoOrdinate)
                .setDuration(0)
                .start()
            MotionEvent.ACTION_UP -> {
                if (alpha > 150) {
                    callback()
                } else {
                    v.animate()
                        .x(0f)
                        .y(0f)
                        .setDuration(100)
                        .start()
                    parentView.background.alpha = 255
                }
                return false
            }
            else -> return false
        }
        return true
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = parentView.context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = parentView.context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

}