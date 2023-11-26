package com.itis.mvicore.domain.first.mvi

import com.badoo.mvicore.element.Reducer

class FirstReducer: Reducer<FirstState, FirstEffect> {
    override fun invoke(state: FirstState, effect: FirstEffect): FirstState =
        when(effect) {
            FirstEffect.StartedLoading -> state.copy(isLoading = true)

            is FirstEffect.LoadedNews -> state.copy(
                news = effect.list,
                isLoading = false
            )

            is FirstEffect.ErrorLoading -> state.copy(isLoading = false)
        }
}
