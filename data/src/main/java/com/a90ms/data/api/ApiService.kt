package com.a90ms.data.api

import com.a90ms.domain.data.entity.game.GameListResponse
import com.a90ms.domain.data.entity.summoner.SummonerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/summoner/genetory")
    suspend fun getSummonerInfo(): SummonerResponse

    @GET("api/summoner/genetory/matches")
    suspend fun getGamesInfo(
        @Query(LAST_MATCH) lastMatch: String
    ): GameListResponse

    companion object {
        const val LAST_MATCH = "lastMatch"
    }
}