package com.stefattorusso.coremvvm.base

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.stefattorusso.coremvvm.di.modules.ViewModelModule
import com.stefattorusso.coremvvm.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = arrayOf(
    ViewModelModule::class
))
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

//        @JvmStatic
//        @Provides
//        @ActivityScope
//        internal fun extras(activity: AppCompatActivity): Bundle? = activity.

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun fragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager
        
//        @JvmStatic
//        @Provides
//        @ActivityScope
//        internal fun errorManager(@Named(ACTIVITY_CONTEXT) context: Context): ErrorManager = ErrorManager(context)

//        @JvmStatic
//        @Provides
//        @ActivityScope
//        internal fun navigationHelper(activity: Activity): NavigationHelper = NavigationHelper(activity)
    }
}