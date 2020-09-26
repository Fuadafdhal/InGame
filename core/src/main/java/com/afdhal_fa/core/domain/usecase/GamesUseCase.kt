package com.afdhal_fa.core.domain.usecase

import com.afdhal_fa.core.data.Resource
import com.afdhal_fa.core.domain.model.Game
import kotlinx.coroutines.flow.Flow


interface GamesUseCase {
    fun getAllGames(page: Int, page_size: Int): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun setFavoriteGames(game: Game, state: Boolean)
    fun clearFavoriteGames()
    fun getDetailGame(id: String): Flow<Resource<Game>>
}