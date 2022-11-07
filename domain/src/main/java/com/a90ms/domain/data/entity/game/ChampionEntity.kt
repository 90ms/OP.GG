package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.ChampionDto

data class ChampionEntity(
    val imageUrl: String,
    val level: Int
) {
    fun toDto() = ChampionDto(
        imageUrl = imageUrl,
        level = level
    )
}
