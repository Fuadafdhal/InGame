package com.afdhal_fa.ingame.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.afdhal_fa.ingame.core.domain.model.Game
import com.afdhal_fa.ingame.core.domain.usecase.GamesUseCase

class DetailGameViewModel @ViewModelInject constructor(private var gameUseCase: GamesUseCase) : ViewModel() {
    fun getDetailGame(id: String) = gameUseCase.getDetailGame(id).asLiveData()
    fun setFavoriteGame(game: Game, newStatus:Boolean) = gameUseCase.setFavoriteGames(game, newStatus)
}