package com.stefattorusso.coremvvm.base.network

import com.stefattorusso.commons.ErrorData
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ApiSingleObserver<T> constructor(private val compositeDisposable: CompositeDisposable) : Observer<T> {

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        onError(ErrorData(throwable = e, message = e.localizedMessage))
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    abstract fun onSuccess(data: T)
    abstract fun onError(e: ErrorData)
}