package com.afdhal_fa.core.utils

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
import com.afdhal_fa.core.data.remote.response.GameResponse
import com.afdhal_fa.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gamesList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                gameId = it.id,
                description = it.description,
                name = it.name,
                released = it.released,
                rating = it.rating,
                imageUrl = it.imageUrl
            )
            gamesList.add(game)
        }
        return gamesList
    }

    fun mapResponsesDetailToEntities(input: GameResponse): GameEntity {
        return GameEntity(
            gameId = input.id,
            description = input.description,
            name = input.name,
            released = input.released,
            rating = input.rating,
            imageUrl = input.imageUrl
        )
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                gameId = it.gameId,
                description = it.description,
                name = it.name,
                released = it.released,
                rating = it.rating,
                imageUrl = it.imageUrl,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        gameId = input.gameId,
        name = input.name,
        description = input.description,
        released = input.released,
        rating = input.rating,
        imageUrl = input.imageUrl,
        isFavorite = input.isFavorite
    )

    fun mapEntityToDomain(input: GameEntity) = Game(
        gameId = input.gameId,
        name = input.name,
        description = input.description,
        released = input.released,
        rating = input.rating,
        imageUrl = input.imageUrl,
        isFavorite = input.isFavorite
    )
}