package com.itis.mvicore.data.news.mapper

import com.itis.newsviewer.data.news.datasource.remote.response.Data
import com.itis.newsviewer.data.news.datasource.remote.response.NewsListResponse
import com.itis.newsviewer.data.news.datasource.remote.response.NewsResponse
import com.itis.newsviewer.domain.news.model.NewsInfo

fun NewsResponse.toNewsInfo(): NewsInfo {
    return NewsInfo(
        uuid = uuid,
        description = description,
        imageUrl = imageUrl,
        snippet = snippet,
        source = source,
        title = title,
        url = url
    )
}

fun Data.toNewsInfo(): NewsInfo {
    return NewsInfo(
        uuid = uuid,
        description = description,
        imageUrl = imageUrl,
        snippet = snippet,
        source = source,
        title = title,
        url = url
    )
}

fun List<Data>.toNewsInfoList(): List<NewsInfo> {
    return map {
        it.toNewsInfo()
    }
}

fun NewsListResponse.toNewsInfoList(): List<NewsInfo> {
    return data.map {
        it.toNewsInfo()
    }
}
