package com.stefattorusso.coremvvm.base.mvvm

import androidx.fragment.app.Fragment
import com.stefattorusso.coremvvm.base.BaseFragmentModule
import dagger.Module

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of [Fragment].
 */
@Module(includes = [BaseFragmentModule::class])
abstract class BaseVMFragmentModule
