package com.afdhal_fa.ingame.core.data.local

import com.afdhal_fa.ingame.core.data.local.entity.GameEntity
import com.afdhal_fa.ingame.core.data.local.room.GamesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor (private val gamesDao: GamesDao) {

    fun getAllGames(): Flow<List<GameEntity>> = gamesDao.getAllGames()
    fun getFavoriteGames(): Flow<List<GameEntity>> = gamesDao.getFavoriteGames()

    suspend fun insertGames(gamesList: List<GameEntity>) = gamesDao.insertGames(gamesList)
    suspend fun updateGame(game: GameEntity) = gamesDao.updateGame(game)

    fun setFavoriteGames(games: GameEntity, newState: Boolean) {
        games.isFavorite = newState
        gamesDao.updateFavoriteGames(games)
    }

    suspend fun clearFavoriteGames() = gamesDao.clearFavoriteGames()

    fun getDetailGame(id: String): Flow<GameEntity> = gamesDao.getDetailGame(id)

}