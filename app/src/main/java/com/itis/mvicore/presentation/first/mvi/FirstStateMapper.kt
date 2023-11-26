package com.itis.mvicore.presentation.first.mvi

import com.itis.mvicore.domain.first.mvi.FirstState

class FirstStateMapper {

    fun map(state: FirstState): FirstScreenUiState = with(state) {
        FirstScreenUiState(
            counter = counter,
            news = news,
            isLoading = isLoading
        )
    }
}
