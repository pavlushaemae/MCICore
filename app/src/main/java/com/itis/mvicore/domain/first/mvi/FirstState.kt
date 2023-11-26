package com.itis.mvicore.domain.first.mvi

import com.itis.newsviewer.domain.news.model.NewsInfo

data class FirstState(
    val counter: Int = 0,
    val news: List<NewsInfo> = emptyList(),
    val isLoading: Boolean = false
)
