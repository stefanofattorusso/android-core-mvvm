package com.stefattorusso.coremvvm.base

import com.stefattorusso.coremvvm.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector

abstract class BaseApplication: DaggerApplication(), HasActivityInjector {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return AppInjector.init(this)
    }

}