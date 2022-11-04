package com.a90ms.data.repository

import com.a90ms.data.api.ApiService
import com.a90ms.domain.repository.ApiRepository

class ApiRepositoryImpl(
    private val apiService: ApiService
): ApiRepository {
    override suspend fun getSummonerInfo() {
        apiService.getSummonerInfo()
    }

    override suspend fun getGameInfoList(createDate: String) {
        apiService.getGamesInfo(createDate)
    }
}