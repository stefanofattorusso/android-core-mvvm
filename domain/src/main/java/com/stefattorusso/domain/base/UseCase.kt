package com.stefattorusso.domain.base

import com.stefattorusso.commons.BaseObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


abstract class UseCase<T, P>(private val compositeDisposable: CompositeDisposable) {

    abstract fun buildUseCaseObservable(params: P): Observable<T>

    fun execute(observer: BaseObserver<T>, params: P) {
        val observable = buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        addDisposable(observable)
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}