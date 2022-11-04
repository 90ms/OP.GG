package com.a90ms.domain.data.entity.summoner

data class LeaguesEntity(
    val hasResults: Boolean,
    val wins: Int,
    val losses: Int,
    val tierRank: TierRankEntity
)
