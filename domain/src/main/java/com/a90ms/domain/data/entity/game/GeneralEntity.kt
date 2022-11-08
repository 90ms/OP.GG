package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.GeneralDto
import com.a90ms.domain.data.dto.game.OpScoreBadgeType

data class GeneralEntity(
    val kill: Float,
    val death: Float,
    val assist: Float,
    val opScoreBadge: String,
    val largestMultiKillString: String,
    val contributionForKillRate: String
) {
    fun toDto() = GeneralDto(
        kill = kill,
        death = death,
        assist = assist,
        opScoreBadge = OpScoreBadgeType.creator(opScoreBadge),
        largestMultiKillString = largestMultiKillString,
        contributionForKillRate = contributionForKillRate,
        contributionForKillRateToString = "킬관여 $contributionForKillRate",
        kdsToString = "$kill / $assist / $death"
    )
}
