package com.stefattorusso.coremvvm.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import timber.log.Timber
import java.io.FileNotFoundException

object BitmapUtils {
    fun decode(context: Context, uri: Uri): Bitmap? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: FileNotFoundException) {
            Timber.e(e)
        }
        return null
    }

    fun rotate(bm: Bitmap?, rotation: Int): Bitmap? {
        if (bm != null) {
            if (rotation != 0) {
                val matrix = Matrix()
                matrix.postRotate(rotation.toFloat())
                return Bitmap.createBitmap(bm, 0, 0, bm.width, bm.height, matrix, true)
            }
        }
        return bm
    }

}