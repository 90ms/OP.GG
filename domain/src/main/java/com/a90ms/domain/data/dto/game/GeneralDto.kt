package com.a90ms.domain.data.dto.game

data class GeneralDto(
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val opScoreBadge: String,
    val contributionForKillRate: String
)
