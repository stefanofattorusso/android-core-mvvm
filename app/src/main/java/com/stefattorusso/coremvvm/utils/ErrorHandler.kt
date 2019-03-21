package com.stefattorusso.coremvvm.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.stefattorusso.coremvvm.R
import com.stefattorusso.data.network.gateway.retrofit.exception.RetrofitException
import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

class ErrorHandler(
    val activity: Activity,
    override val key: CoroutineContext.Key<*>
) : CoroutineExceptionHandler {

    private val mErrorList = mutableSetOf<BundleError>()

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        var code = 0
        val title: String = activity.getString(R.string.unespected_error_title)
        var message: String = activity.getString(R.string.unespected_error_message)
        when (exception) {
            is RetrofitException -> {
                code = exception.response?.code() ?: 0
                when (exception.cause) {
                    is ConnectException -> message = activity.getString(R.string.unespected_timeout_message)
                    is SocketTimeoutException -> message = activity.getString(R.string.unespected_timeout_message)
                    is UnknownHostException -> message = activity.getString(R.string.unespected_timeout_message)
                    else -> {

                    }
                }
            }
        }
        set(code, title, message)
    }

    fun set(code: Int, title: Int, message: Int) {
        handleError(BundleError(code, activity.getString(title), activity.getString(message)))
    }

    fun set(code: Int, title: String, message: String) {
        handleError(BundleError(code, title, message))
    }

    @Synchronized
    private fun handleError(error: BundleError) {
        if (mErrorList.isEmpty()) {
            mErrorList.add(error)
            showError(error)
        } else mErrorList.add(error)
    }

    @Synchronized
    private fun showError(error: BundleError) {
        AlertDialog.Builder(activity).apply {
            setTitle(error.title)
            setMessage(error.message)
            setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
            setOnDismissListener {
                mErrorList.remove(mErrorList.first())
                if (mErrorList.isNotEmpty()) showError(mErrorList.first())
            }
        }.show()
    }

    private inner class BundleError(val code: Int, val title: String, val message: String)
}