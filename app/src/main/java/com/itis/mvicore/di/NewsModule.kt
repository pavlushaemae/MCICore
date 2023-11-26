package com.itis.mvicore.di

import com.itis.mvicore.data.news.NewsApi
import com.itis.mvicore.data.news.NewsRepositoryImpl
import com.itis.mvicore.domain.first.GetNewsByIdUseCase
import com.itis.mvicore.domain.first.GetNewsUseCase
import com.itis.mvicore.domain.first.NewsRepository
import org.koin.dsl.module

val newsModule = module {

    single { provideNewsRepository(get()) }
    factory { provideGetNewsUseCase(get()) }
    factory { provideGetNewsByIdUseCase(get()) }
}

private fun provideNewsRepository(
    newsApi: NewsApi,
): NewsRepository = NewsRepositoryImpl(newsApi)


private fun provideGetNewsUseCase(
    newsRepository: NewsRepository,
): GetNewsUseCase = GetNewsUseCase(newsRepository)

private fun provideGetNewsByIdUseCase(
    newsRepository: NewsRepository,
): GetNewsByIdUseCase = GetNewsByIdUseCase(newsRepository)
