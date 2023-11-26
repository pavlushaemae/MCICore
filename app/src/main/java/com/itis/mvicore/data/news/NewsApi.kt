package com.itis.mvicore.data.news

import com.itis.newsviewer.data.news.datasource.remote.response.NewsListResponse
import com.itis.newsviewer.data.news.datasource.remote.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @GET("uuid/{uuid}")
    fun getNewsById(
        @Path("uuid") uuid: String,
    ): Single<NewsResponse>

    @GET("all")
    fun getNewsPage(
        @Query("page") page: Int = 1,
        @Query(value = "limit") limit: Int = 10,
    ): Single<NewsListResponse>

}
