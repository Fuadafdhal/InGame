package com.afdhal_fa.core.utils

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