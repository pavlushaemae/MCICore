package com.itis.mvicore.domain.first

import com.itis.newsviewer.domain.news.model.NewsInfo
import io.reactivex.Single

class GetNewsUseCase(
    private val repository: NewsRepository,
) {
    operator fun invoke(): Single<List<NewsInfo>> {
        return repository.getNewsList()
    }
}
