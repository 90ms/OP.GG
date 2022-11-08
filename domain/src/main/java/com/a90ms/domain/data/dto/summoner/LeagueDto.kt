package com.a90ms.domain.data.dto.summoner

data class LeagueDto(
    val hasResults: Boolean,
    val wins: Float,
    val losses: Float,
    val tierRank: TierRankDto,
    val winningRate: String
)
