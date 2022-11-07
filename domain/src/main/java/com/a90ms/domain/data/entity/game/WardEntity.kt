package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.WardDto

data class WardEntity(
    val sightWardsBought: Int,
    val visionWardsBought: Int
) {
    fun toDto() = WardDto(
        sightWardsBought = sightWardsBought,
        visionWardsBought = visionWardsBought
    )
}
