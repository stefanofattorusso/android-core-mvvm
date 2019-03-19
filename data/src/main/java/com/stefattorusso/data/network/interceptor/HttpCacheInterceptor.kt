package com.stefattorusso.data.network.interceptor

import android.content.Context
import com.stefattorusso.commons.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

class HttpCacheInterceptor(
    private val context: Context
): Interceptor {

    var cacheMaxAge: Int = 0
    var cacheMaxStale: Int = 0

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.d("[intercept] %s", chain.request().url().toString())
        val originalResponse = chain.proceed(chain.request())
        return if (context.isNetworkAvailable()) {
            originalResponse.newBuilder()
                .header("Cache-Control", "public, max-age=" + if (cacheMaxAge > 0) cacheMaxAge else DEFAULT_CACHE_MAX_AGE)
                .build()
        } else {
            originalResponse.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + if (cacheMaxStale > 0) cacheMaxStale else DEFAULT_CACHE_MAX_STALE)
                .build()
        }
    }

    companion object {
        private const val DEFAULT_CACHE_MAX_AGE = 60 * 10 // read from cache for 10 minutes
        private const val DEFAULT_CACHE_MAX_STALE = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
    }
}