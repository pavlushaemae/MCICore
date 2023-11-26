package com.itis.mvicore.domain.first.mvi

import com.badoo.mvicore.element.NewsPublisher

class FirstNewsPublisher: NewsPublisher<FirstWish, FirstEffect, FirstState, FirstNews> {
    override fun invoke(action: FirstWish, effect: FirstEffect, state: FirstState): FirstNews? =
        when(effect) {
            is FirstEffect.ErrorLoading -> FirstNews.ErrorDownloadingNews(effect.message)
            else -> null
        }
}
