package com.afdhal_fa.core.data.local.room

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

import androidx.room.*
import com.afdhal_fa.core.data.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Query("SELECT * FROM inGame")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM inGame where isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGame(game: GameEntity)

    @Update
    fun updateFavoriteGames(game: GameEntity)

    @Query("SELECT * FROM inGame WHERE id = :id")
    fun getDetailGame(id: String): Flow<GameEntity>
}
