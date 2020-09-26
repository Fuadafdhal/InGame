package com.afdhal_fa.ingame.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.afdhal_fa.core.domain.usecase.GamesUseCase

class HomeViewModel @ViewModelInject constructor(gamesUseCase: GamesUseCase) : ViewModel() {
    val games = gamesUseCase.getAllGames(1, 25).asLiveData()

}