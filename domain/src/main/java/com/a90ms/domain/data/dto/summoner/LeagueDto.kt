package com.a90ms.domain.data.dto.summoner

data class LeagueDto(
    val hasResults: Boolean,
    val wins: Int,
    val losses: Int,
    val tierRank: TierRankDto,
    val winningRate: String
)
