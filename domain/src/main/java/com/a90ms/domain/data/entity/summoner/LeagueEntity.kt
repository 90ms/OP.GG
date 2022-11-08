package com.a90ms.domain.data.entity.summoner

import com.a90ms.domain.data.dto.summoner.LeagueDto
import kotlin.math.roundToInt

data class LeagueEntity(
    val hasResults: Boolean,
    val wins: Float,
    val losses: Float,
    val tierRank: TierRankEntity
) {
    fun toDto() = LeagueDto(
        hasResults = hasResults,
        wins = wins,
        losses = losses,
        tierRank = tierRank.toDto(),
        winningRate = "$wins 승 $losses 패 ${winRate()}"
    )

    private fun winRate() = "(${((wins / (wins + losses)) * 100).roundToInt()}%)"
}
