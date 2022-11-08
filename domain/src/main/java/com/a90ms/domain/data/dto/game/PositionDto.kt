package com.a90ms.domain.data.dto.game

data class PositionDto(
    val games: Int,
    val wins: Int,
    val losses: Int,
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
