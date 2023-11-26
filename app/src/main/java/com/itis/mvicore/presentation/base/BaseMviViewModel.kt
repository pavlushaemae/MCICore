package com.itis.mvicore.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itis.mvicore.presentation.utils.Event
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseMviViewModel<T: Any>: ViewModel(), Observer<T> {
    protected val disposables = CompositeDisposable()

    protected val _errorLiveData = MutableLiveData<Event<String>>()
    val errorLiveData: LiveData<Event<String>>
        get() = _errorLiveData

    override fun onSubscribe(disposable: Disposable) {
        disposables += disposable
    }

    override fun onError(e: Throwable) {
        _errorLiveData.postValue(Event(e.message ?: "ERROR"))
    }

    override fun onComplete() = Unit

    override fun onNext(t: T) = Unit

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) disposables.dispose()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        add(disposable)
    }
}
