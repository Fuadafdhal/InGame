package com.afdhal_fa.core.domain.repository

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

import com.afdhal_fa.core.data.Resource
import com.afdhal_fa.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGamesRepository {

    fun getAllGames(page: Int, page_size: Int): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun setFavoriteGames(game: Game, state: Boolean)
    fun getDetailGame(id: String): Flow<Resource<Game>>
}