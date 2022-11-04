package com.a90ms.data.api

import com.a90ms.domain.data.entity.game.GamesEntity
import com.a90ms.domain.data.entity.summoner.SummonerEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/summoner/genetory")
    suspend fun getSummonerInfo(): SummonerEntity

    @GET("api/summoner/genetory/matches?lastMatch={createDate}")
    suspend fun getGamesInfo(
        @Query(LAST_MATCH) lastMatch: String
    ): GamesEntity

    companion object {
        const val LAST_MATCH = "lastMatch"
    }
}