package com.itis.mvicore.domain.first

import com.itis.newsviewer.domain.news.model.NewsInfo
import io.reactivex.Single

class GetNewsByIdUseCase(
    private val repository: NewsRepository,
) {
    operator fun invoke(
        id: String,
    ): Single<NewsInfo> {
        return repository.getNewsById(id)
    }
}
