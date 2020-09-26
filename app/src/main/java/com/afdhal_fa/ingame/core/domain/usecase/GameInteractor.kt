package com.afdhal_fa.ingame.core.domain.usecase

import com.afdhal_fa.ingame.core.data.Resource
import com.afdhal_fa.ingame.core.domain.model.Game
import com.afdhal_fa.ingame.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor (private val gamesRepository: IGamesRepository) : GamesUseCase {
    override fun getAllGames(page: Int, page_size: Int): Flow<Resource<List<Game>>> =
        gamesRepository.getAllGames(page, page_size)

    override fun getDetailGame(id: String): Flow<Resource<Game>> = gamesRepository.getDetailGame(id)

    override fun getFavoriteGames(): Flow<List<Game>> = gamesRepository.getFavoriteGames()

    override fun setFavoriteGames(game: Game, state: Boolean) =
        gamesRepository.setFavoriteGames(game, state)

    override fun clearFavoriteGames() = gamesRepository.clearFavoriteGames()



}