package com.afdhal_fa.ingame.di

/**
 * Copyright 2020 Muh Fuad Afdhal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import com.afdhal_fa.core.domain.usecase.GameInteractor
import com.afdhal_fa.core.domain.usecase.GamesUseCase
import com.afdhal_fa.ingame.detail.DetailGameViewModel
import com.afdhal_fa.ingame.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GamesUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailGameViewModel(get()) }
}