package com.a90ms.domain.data.dto.game

data class GameResponseDto(
    val games: List<GameDto>,
    val champions: List<ChampionsDto>,
    val positions: List<PositionDto>,
    val summary: SummaryDto
)
