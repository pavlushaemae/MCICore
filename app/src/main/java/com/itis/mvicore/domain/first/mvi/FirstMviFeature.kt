package com.itis.mvicore.domain.first.mvi

import com.badoo.mvicore.feature.ActorReducerFeature
import com.itis.mvicore.domain.first.GetNewsUseCase

class FirstMviFeature(
    getNewsUseCase: GetNewsUseCase,
) : ActorReducerFeature<FirstWish, FirstEffect, FirstState, FirstNews>(
    initialState = FirstState(),
    actor = FirstActor(getNewsUseCase),
    reducer = FirstReducer(),
    newsPublisher = FirstNewsPublisher()

)
