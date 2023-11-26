package com.itis.mvicore.domain.first.mvi

import com.itis.newsviewer.domain.news.model.NewsInfo

sealed class FirstEffect {
    object StartedLoading : FirstEffect()
    data class LoadedNews(val list: List<NewsInfo>) : FirstEffect()
    data class ErrorLoading(val message: String) : FirstEffect()
}
