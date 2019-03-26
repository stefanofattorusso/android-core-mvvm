package com.stefattorusso.coremvvm.base

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.stefattorusso.commons.lifecyclehelpers.autoinflate.AutoInflateHelper
import com.stefattorusso.commons.lifecyclehelpers.autoinflate.AutoInflateHelperCallback
import com.stefattorusso.commons.lifecyclehelpers.autoinflate.AutoInflateHelperImpl
import com.stefattorusso.commons.lifecyclehelpers.fullscreen.FullScreenActivityLifecycle
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelper
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperCallback
import com.stefattorusso.commons.lifecyclehelpers.injectfragment.InjectFragmentHelperImpl
import com.stefattorusso.coremvvm.di.modules.ViewModelModule
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import com.stefattorusso.coremvvm.helpers.NavigationHelper
import com.stefattorusso.coremvvm.utils.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Named

@Module(
    includes = arrayOf(
        ViewModelModule::class
    )
)
abstract class BaseActivityModule {

    @Binds
    @Named(ACTIVITY_CONTEXT)
    @ActivityScope
    internal abstract fun context(activity: Activity): Context

    @Module
    companion object {

        const val ACTIVITY_CONTEXT = "BaseActivityModule.activityContext"

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun activity(activity: Activity): AppCompatActivity = activity as AppCompatActivity

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun resources(activity: AppCompatActivity): Resources = activity.resources

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun fragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun errorHandler(activity: Activity): ErrorHandler = ErrorHandler(activity, CoroutineExceptionHandler)

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun injectFragmentHelper(activity: Activity): InjectFragmentHelper =
            InjectFragmentHelperImpl(activity as AppCompatActivity, activity as InjectFragmentHelperCallback<*>)

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun autoInflateHelper(activity: Activity): AutoInflateHelper =
            AutoInflateHelperImpl(activity as AppCompatActivity, activity as AutoInflateHelperCallback)

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun navigationHelper(activity: Activity): NavigationHelper = NavigationHelper(activity)

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun fullScreenActivityLifecycle(activity: Activity): FullScreenActivityLifecycle =
            FullScreenActivityLifecycle(activity as AppCompatActivity)
    }
}