package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.PositionDto
import com.a90ms.domain.data.dto.game.PositionType
import kotlin.math.roundToInt

data class PositionEntity(
    val games: Float,
    val wins: Float,
    val losses: Float,
    val position: String,
    val positionName: String
) {
    fun toDto() = PositionDto(
        games = games,
        wins = wins,
        losses = losses,
        position = position,
        positionName = positionName,
        positionType = PositionType.creator(positionName),
        rate = "${((games / 20.0) * 100).roundToInt()}%"
    )
}
