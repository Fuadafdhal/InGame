package com.afdhal_fa.core.data

import com.afdhal_fa.core.data.local.LocalDataSource
import com.afdhal_fa.core.data.remote.RemoteDataSource
import com.afdhal_fa.core.data.remote.network.ApiResponse
import com.afdhal_fa.core.data.remote.response.GameResponse
import com.afdhal_fa.core.domain.model.Game
import com.afdhal_fa.core.domain.repository.IGamesRepository
import com.afdhal_fa.core.utils.AppExecutors
import com.afdhal_fa.core.utils.DataMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepository @Inject constructor  (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGamesRepository {

    override fun getAllGames(page: Int, page_size: Int): Flow<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGames().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGames(page, page_size)

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(gameList)
            }
        }.asFlow()
    }

    override fun getFavoriteGames(): Flow<List<Game>> =
        localDataSource.getFavoriteGames().map { DataMapper.mapEntitiesToDomain(it) }

    override fun setFavoriteGames(game: Game, state: Boolean) {
        val gamesEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGames(gamesEntity, state) }
    }

    override fun clearFavoriteGames() {
        GlobalScope.launch {
            localDataSource.clearFavoriteGames()
        }
    }

    override fun getDetailGame(id: String): Flow<Resource<Game>> {
        return object : NetworkBoundResource<Game, GameResponse>() {
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getDetailGame(id).map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: Game?): Boolean = data === null || data.description === null

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> = remoteDataSource.getDetailGame(id)

            override suspend fun saveCallResult(data: GameResponse) {
                val gameDetail = DataMapper.mapResponsesDetailToEntities(data)
                localDataSource.updateGame(gameDetail)
            }
        }.asFlow()
    }
}