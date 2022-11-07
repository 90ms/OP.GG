package com.a90ms.domain.repository

import com.a90ms.domain.data.dto.game.GameDto
import com.a90ms.domain.data.dto.summoner.SummonerDto

interface ApiRepository {

    suspend fun getSummonerInfo(): SummonerDto
    suspend fun getGameInfoList(createDate: String): List<GameDto>
}
