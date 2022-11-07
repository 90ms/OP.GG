package com.a90ms.domain.data.entity.summoner

import com.a90ms.domain.data.dto.summoner.LeagueDto

data class LeagueEntity(
    val hasResults: Boolean,
    val wins: Int,
    val losses: Int,
    val tierRank: TierRankEntity
) {
    fun toDto() = LeagueDto(
        hasResults = hasResults,
        wins = wins,
        losses = losses,
        tierRank = tierRank.toDto()
    )
}
