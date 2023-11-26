package com.itis.mvicore.di

import com.itis.mvicore.presentation.first.firstModule
import org.koin.dsl.module

val featureModule = module {
    includes(
        firstModule
    )
}
