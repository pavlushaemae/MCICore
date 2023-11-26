package com.itis.mvicore.presentation.first.mvi

import com.itis.newsviewer.domain.news.model.NewsInfo

data class FirstScreenUiState(
    val counter: Int = 0,
    val news: List<NewsInfo> = emptyList(),
    val isLoading: Boolean = false
)
