package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.SummaryDto

data class SummaryEntity(
    val wins: Int,
    val losses: Int,
    val kills: Int,
    val deaths: Int,
    val assists: Int
) {
    fun toDto() = SummaryDto(
        wins = wins,
        losses = losses,
        kills = kills,
        deaths = deaths,
        assists = assists
    )
}
