package com.itis.mvicore.data.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter("api_token", "Da6OkaIHSgRKq7zMqgW8nxfisdEEM1LpTNPTYxWe")
            .build()
        return chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }
}
