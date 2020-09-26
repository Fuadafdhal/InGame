package com.afdhal_fa.core.data.local.room

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

    @Query("UPDATE inGame SET isFavorite = 0 where isFavorite = 1")
    suspend fun clearFavoriteGames()

    @Query("SELECT * FROM inGame WHERE id = :id")
    fun getDetailGame(id: String): Flow<GameEntity>
}
