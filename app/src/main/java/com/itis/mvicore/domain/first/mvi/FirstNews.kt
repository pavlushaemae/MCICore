package com.itis.mvicore.domain.first.mvi

sealed class FirstNews {
    data class ErrorDownloadingNews(val message: String) : FirstNews()
}
