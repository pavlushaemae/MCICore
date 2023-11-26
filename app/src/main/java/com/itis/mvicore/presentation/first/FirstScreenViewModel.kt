package com.itis.mvicore.presentation.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itis.mvicore.domain.first.mvi.FirstMviFeature
import com.itis.mvicore.domain.first.mvi.FirstNews
import com.itis.mvicore.domain.first.mvi.FirstState
import com.itis.mvicore.domain.first.mvi.FirstWish
import com.itis.mvicore.presentation.base.BaseMviViewModel
import com.itis.mvicore.presentation.first.mvi.Actions
import com.itis.mvicore.presentation.first.mvi.FirstScreenUiState
import com.itis.mvicore.presentation.first.mvi.FirstStateMapper
import com.itis.mvicore.presentation.utils.Event
import com.itis.newsviewer.domain.news.model.NewsInfo
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class FirstScreenViewModel(
    private val feature: FirstMviFeature,
    private val stateMapper: FirstStateMapper
): BaseMviViewModel<FirstState>() {

    private val uiState = MutableLiveData<FirstScreenUiState>()
    val news  = MutableLiveData<Actions>()

    init {
        subscribeToFeature()
        loadNews()
    }

    private fun subscribeToFeature() {
        feature.subscribe(this)
        feature.news.subscribe(object : Observer<FirstNews> {
            override fun onSubscribe(disposable: Disposable) {
                disposables += disposable
            }

            override fun onError(e: Throwable) {
                _errorLiveData.postValue(Event(e.message ?: ""))
            }

            override fun onComplete() = Unit
            override fun onNext(news: FirstNews) = onNextNews(news)

        })
    }

    fun getStateObserver(): LiveData<FirstScreenUiState> = uiState

    private fun onNextNews(newNews: FirstNews) = when(newNews) {
        is FirstNews.ErrorDownloadingNews ->
            news.postValue(Actions.ShowDialog(newNews.message))


    }

    override fun onNext(state: FirstState) {
        uiState.postValue(stateMapper.map(state))
    }

    fun onNewsItemClick(newsInfo: NewsInfo) {

    }

    fun onReloadClick() {
        loadNews()
    }

    private fun loadNews() {
        feature.accept(FirstWish.LoadNewsList)
    }
}
