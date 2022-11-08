package com.a90ms.domain.data.dto.game

data class PositionDto(
    val games: Float,
    val wins: Float,
    val losses: Float,
    val position: String,
    val positionName: String,
    val positionType: PositionType,
    val rate: String
)

enum class PositionType(val value: String) {
    NULL(""),
    TOP("Top"),
    JUNGLE("Jungle"),
    SUPPORT("Support"),
    BOTTOM("Bottom"),
    MIDDLE("Middle");

    companion object {
        fun creator(type: String): PositionType = values().firstOrNull { it.value == type } ?: NULL
    }
}
