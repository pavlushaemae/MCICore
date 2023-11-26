package com.itis.mvicore.data.news

import com.itis.mvicore.data.news.mapper.toNewsInfo
import com.itis.mvicore.data.news.mapper.toNewsInfoList
import com.itis.mvicore.domain.first.NewsRepository
import com.itis.newsviewer.domain.news.model.NewsInfo
import io.reactivex.Single

class NewsRepositoryImpl(
    private val api: NewsApi,
) : NewsRepository {
    override fun getNewsById(uuid: String): Single<NewsInfo> {
        return api.getNewsById(uuid).map { it.toNewsInfo() }
    }

    override fun getNewsList(): Single<List<NewsInfo>> {
        return api.getNewsPage().map { it.toNewsInfoList() }
    }
}
