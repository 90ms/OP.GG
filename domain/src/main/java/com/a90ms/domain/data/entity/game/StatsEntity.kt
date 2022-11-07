package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.StatsDto

data class StatsEntity(
    val general: GeneralEntity,
    val ward: WardEntity
) {
    fun toDto() = StatsDto(
        general = general.toDto(),
        ward = ward.toDto()
    )
}
