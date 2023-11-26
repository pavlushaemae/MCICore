package com.itis.mvicore.presentation.first.mvi

sealed interface Actions {
    data class ShowDialog(val message: String) : Actions
}
