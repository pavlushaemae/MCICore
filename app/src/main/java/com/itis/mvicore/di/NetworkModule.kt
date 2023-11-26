package com.itis.mvicore.di

import com.itis.mvicore.data.interceptor.ApiKeyInterceptor
import com.itis.mvicore.data.interceptor.LanguageInterceptor
import com.itis.mvicore.data.news.NewsApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single(
        named("apiKey")
    ) { provideApiKeyInterceptor() }
    single(
        named("language")
    ) { provideLanguageInterceptor() }
    single(
        named("logging")
    ) { provideLoggingInterceptor() }
    single { provideGsonConverterFactory() }
    single { provideBaseUrl() }
    single {
        provideHttpClient(
            get(named("apiKey")),
            get(named("logging")),
            get(named("language"))
        )
    }
    single { provideRetrofit(get(), get(), get()) }
    single { provideNewsApi(get()) }
}

fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
}

fun provideApiKeyInterceptor(): Interceptor = ApiKeyInterceptor()

fun provideLanguageInterceptor(): Interceptor = LanguageInterceptor()

fun provideHttpClient(
    apiKeyInterceptor: Interceptor,
    loggingInterceptor: Interceptor,
    languageInterceptor: Interceptor,
): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor(apiKeyInterceptor)
    .addInterceptor(languageInterceptor)
    .connectTimeout(10L, TimeUnit.SECONDS)
    .build()

fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideBaseUrl(): String = "https://api.thenewsapi.com/v1/news/"

fun provideRetrofit(
    httpClient: OkHttpClient,
    gsonFactory: GsonConverterFactory,
    baseUrl: String,
): Retrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(baseUrl)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(gsonFactory)
    .build()

fun provideNewsApi(
    retrofit: Retrofit,
): NewsApi = retrofit.create(NewsApi::class.java)
