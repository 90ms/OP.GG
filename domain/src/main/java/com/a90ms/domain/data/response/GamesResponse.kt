package com.a90ms.domain.data.response

import com.a90ms.domain.data.dto.game.GameResponseDto
import com.a90ms.domain.data.entity.game.ChampionsEntity
import com.a90ms.domain.data.entity.game.GameEntity
import com.a90ms.domain.data.entity.game.PositionEntity
import com.a90ms.domain.data.entity.game.SummaryEntity

data class GamesResponse(
    val games: List<GameEntity>,
    val champions: List<ChampionsEntity>,
    val positions: List<PositionEntity>,
    val summary: SummaryEntity
) {
    fun toDto() = GameResponseDto(
        games = games.map(GameEntity::toDto),
        champions = champions.map(ChampionsEntity::toDto),
        positions = positions.map(PositionEntity::toDto),
        summary = summary.toDto(),
    )
}
