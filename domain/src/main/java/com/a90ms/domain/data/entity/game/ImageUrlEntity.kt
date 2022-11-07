package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.ImageUrlDto

data class ImageUrlEntity(
    val imageUrl: String
) {
    fun toDto() = ImageUrlDto(
        imageUrl = imageUrl
    )
}
