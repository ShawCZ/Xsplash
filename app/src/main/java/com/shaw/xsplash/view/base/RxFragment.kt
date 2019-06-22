package com.shaw.xsplash.view.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxFragment<VB : ViewDataBinding> : BaseFragment<VB>() {

    var untilDetachDisposables = CompositeDisposable()
        private set

    var untilDestroyDisposables = CompositeDisposable()
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (untilDetachDisposables.isDisposed) {
            untilDetachDisposables = CompositeDisposable()
        }
    }

    override fun onDetach() {
        super.onDetach()
        untilDetachDisposables.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (untilDestroyDisposables.isDisposed) {
            untilDestroyDisposables = CompositeDisposable()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        untilDestroyDisposables.clear()
    }

    fun <T> Observable<T>.subscribeUntilDetach(): Disposable {
        return subscribe()
            .also { untilDetachDisposables.add(it) }
    }

    fun <T> Observable<T>.subscribeUntilDetach(onNext: (T) -> Unit): Disposable {
        return subscribe(onNext)
            .also { untilDetachDisposables.add(it) }
    }

    fun <T> Observable<T>.subscribeUntilDetach(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return subscribe(onNext, onError)
            .also { untilDetachDisposables.add(it) }
    }

    fun <T> Observable<T>.subscribeUntilDetach(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onCompleted: () -> Unit
    ): Disposable {
        return subscribe(onNext, onError, onCompleted)
            .also { untilDetachDisposables.add(it) }
    }

    fun <T> Observable<T>.subscribeUntilDestroy(): Disposable {
        return subscribe()
            .also { untilDestroyDisposables.add(it) }
    }

    fun <T> Observable<T>.subscribeUntilDestroy(onNext: (T) -> Unit): Disposable {
        return subscribe(onNext)
            .also { untilDestroyDisposables.add(it) }
    }

    fun <T> Observable<T>.subscribeUntilDestroy(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return subscribe(onNext, onError)
            .also { untilDestroyDisposables.add(it) }
    }

    fun <T> Observable<T>.subscribeUntilDestroy(
        onNext: (T) -> Unit,
        onError: (Throwable) -> Unit,
        onCompleted: () -> Unit
    ): Disposable {
        return subscribe(onNext, onError, onCompleted)
            .also { untilDestroyDisposables.add(it) }
    }

}