package com.afdhal_fa.core.data.local

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

import com.afdhal_fa.core.data.local.entity.GameEntity
import com.afdhal_fa.core.data.local.room.GamesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gamesDao: GamesDao) {

    fun getAllGames(): Flow<List<GameEntity>> = gamesDao.getAllGames()
    fun getFavoriteGames(): Flow<List<GameEntity>> = gamesDao.getFavoriteGames()

    suspend fun insertGames(gamesList: List<GameEntity>) = gamesDao.insertGames(gamesList)
    suspend fun updateGame(game: GameEntity) = gamesDao.updateGame(game)

    fun setFavoriteGames(games: GameEntity, newState: Boolean) {
        games.isFavorite = newState
        gamesDao.updateFavoriteGames(games)
    }

    fun getDetailGame(id: String): Flow<GameEntity> = gamesDao.getDetailGame(id)

}