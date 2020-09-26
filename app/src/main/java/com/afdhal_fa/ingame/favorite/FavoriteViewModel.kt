package com.afdhal_fa.ingame.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.afdhal_fa.core.domain.usecase.GamesUseCase

class FavoriteViewModel @ViewModelInject constructor(private val gamesUseCase: GamesUseCase) :
    ViewModel() {
    val favoriteGames = gamesUseCase.getFavoriteGames().asLiveData()
    fun clearFavoriteGames() = gamesUseCase.clearFavoriteGames()
}