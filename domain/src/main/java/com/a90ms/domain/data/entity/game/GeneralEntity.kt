package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.GeneralDto

data class GeneralEntity(
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val opScoreBadge: String,
    val largestMultiKillString: String,
    val contributionForKillRate: String
) {
    fun toDto() = GeneralDto(
        kills = kills,
        deaths = deaths,
        assists = assists,
        opScoreBadge = opScoreBadge,
        largestMultiKillString = largestMultiKillString,
        contributionForKillRate = contributionForKillRate
    )
}
