package com.itis.mvicore.domain.first.mvi

import com.badoo.mvicore.element.Actor
import com.itis.mvicore.domain.first.GetNewsUseCase
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FirstActor(
    private val getNewsUseCase: GetNewsUseCase,
) : Actor<FirstState, FirstWish, FirstEffect> {
    override fun invoke(state: FirstState, wish: FirstWish): Observable<out FirstEffect> =
        when (wish) {
            is FirstWish.LoadNewsList -> getNewsUseCase().toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { FirstEffect.LoadedNews(it) as FirstEffect }
                .startWith(just(FirstEffect.StartedLoading))
                .onErrorReturn { FirstEffect.ErrorLoading(it.message ?: "") }

        }
}
