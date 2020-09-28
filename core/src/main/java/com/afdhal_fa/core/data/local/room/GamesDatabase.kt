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

import androidx.room.Database
import androidx.room.RoomDatabase
import com.afdhal_fa.core.data.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 3, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

}