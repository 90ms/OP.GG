package com.a90ms.domain.repository

interface ApiRepository {

    suspend fun getSummonerInfo()
    suspend fun getGameInfoList(createDate: String)
}