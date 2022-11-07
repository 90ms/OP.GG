package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.PositionDto

data class PositionEntity(
    val games: Int,
    val wins: Int,
    val losses: Int,
    val position: String,
    val positionName: String
) {
    fun toDto() = PositionDto(
        games = games,
        wins = wins,
        losses = losses,
        position = position,
        positionName = positionName
    )
}
