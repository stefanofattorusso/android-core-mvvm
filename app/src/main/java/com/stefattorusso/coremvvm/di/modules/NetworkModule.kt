package com.stefattorusso.coremvvm.di.modules

import android.content.Context
import com.stefattorusso.coremvvm.BuildConfig
import com.stefattorusso.coremvvm.R
import com.stefattorusso.data.coroutines.CoroutineDispatchers
import com.stefattorusso.data.network.gateway.AppGateway
import com.stefattorusso.data.network.gateway.retrofit.AppRetrofitGateway
import com.stefattorusso.data.network.gateway.retrofit.service.AppRetrofitService
import com.stefattorusso.data.network.interceptor.HttpCacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@Suppress("unused")
object NetworkModule {

    private const val CACHE_MAX_AGE = 60 * 10               // read from cache for 10 minutes
    private const val CACHE_MAX_STALE = 60 * 60 * 24 * 28   // tolerate 4-weeks stale
    private const val TIMEOUT = 35                          // 30 sec
    private const val CONNECTION_TIMEOUT = 15               // 10 sec

    @JvmStatic
    @Provides
    @Reusable
    internal fun httpLoggingInterceptorLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    // Interceptors ====================================================================================================

    @JvmStatic
    @Provides
    @Reusable
    internal fun httpCacheInterceptor(context: Context): HttpCacheInterceptor {
        return HttpCacheInterceptor(context).apply {
            cacheMaxAge = CACHE_MAX_AGE
            cacheMaxStale = CACHE_MAX_STALE
        }
    }

    @JvmStatic
    @Provides
    @Reusable
    internal fun httpLoggingInterceptor(loggingLevel: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = loggingLevel
        }
    }

    // OKHttpClient ====================================================================================================

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache,
        cacheInterceptor: HttpCacheInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addNetworkInterceptor(cacheInterceptor)
            .cache(cache)
            .retryOnConnectionFailure(true)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }

    // Retrofit ========================================================================================================

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofit(context: Context, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_domain))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    // RetrofitService =================================================================================================

    @JvmStatic
    @Provides
    @Reusable
    internal fun appRetrofitService(retrofit: Retrofit): AppRetrofitService {
        return retrofit.create(AppRetrofitService::class.java)
    }

    // Gateway =========================================================================================================

    @JvmStatic
    @Provides
    @Reusable
    internal fun appGateway(service: AppRetrofitService, dispatchers: CoroutineDispatchers): AppGateway {
        return AppRetrofitGateway(service, dispatchers)
    }
}