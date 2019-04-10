package com.stefattorusso.coremvvm

import com.stefattorusso.coremvvm.base.BaseApplication
import timber.log.Timber


class MVVMApplication : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        } else {
            //todo
        }
    }
}