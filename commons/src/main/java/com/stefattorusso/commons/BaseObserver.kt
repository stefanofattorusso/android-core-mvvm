package com.stefattorusso.commons

import io.reactivex.observers.DisposableObserver

abstract class BaseObserver<T> : DisposableObserver<T>() {

    override fun onComplete() {

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