package com.afdhal_fa.core.data.remote

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

import android.util.Log
import com.afdhal_fa.core.data.remote.network.ApiResponse
import com.afdhal_fa.core.data.remote.network.ApiService
import com.afdhal_fa.core.data.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllGames(page: Int, page_size: Int): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getList(page, page_size)
                val dataArray = response.results

                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGame(id: String): Flow<ApiResponse<GameResponse>> {
        return flow {
            try {
                val response = apiService.getDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e: UnknownHostException) {
                emit(ApiResponse.Error("Something went wrong.\nReconnect to the Internet and Try again"))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}