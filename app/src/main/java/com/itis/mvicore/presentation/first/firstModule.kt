package com.itis.mvicore.presentation.first

import com.itis.mvicore.domain.first.mvi.FirstMviFeature
import com.itis.mvicore.presentation.first.mvi.FirstStateMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val firstModule = module {
    viewModel { FirstScreenViewModel(get(), get()) }
    single { FirstMviFeature(get()) }
    single { FirstStateMapper() }
}
