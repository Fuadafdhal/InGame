package com.afdhal_fa.ingame.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.afdhal_fa.ingame.core.data.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 3, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

}