package com.a90ms.domain.data.response

import com.a90ms.domain.data.entity.game.GameEntity

data class GamesResponse(
    val games: List<GameEntity>
)