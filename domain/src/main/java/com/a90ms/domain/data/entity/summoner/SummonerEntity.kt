package com.a90ms.domain.data.entity.summoner

data class SummonerEntity(
    val name: String,
    val level: String,
    val profileImageUrl: String,
    val leagues: List<LeaguesEntity>
)
