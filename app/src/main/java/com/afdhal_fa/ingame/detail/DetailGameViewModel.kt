package com.afdhal_fa.ingame.detail

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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.afdhal_fa.core.domain.model.Game
import com.afdhal_fa.core.domain.usecase.GamesUseCase

class DetailGameViewModel(private var gameUseCase: GamesUseCase) : ViewModel() {
    fun getDetailGame(id: String) = gameUseCase.getDetailGame(id).asLiveData()
    fun setFavoriteGame(game: Game, newStatus: Boolean) =
        gameUseCase.setFavoriteGames(game, newStatus)
}