package com.itis.mvicore.domain.first

import com.itis.newsviewer.domain.news.model.NewsInfo
import io.reactivex.Single

interface NewsRepository {
    fun getNewsById(uuid: String): Single<NewsInfo>
    fun getNewsList(): Single<List<NewsInfo>>
}
