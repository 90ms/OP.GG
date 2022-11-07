package com.a90ms.domain.data.dto.game

data class ChampionsDto(
    val id: Long,
    val key: String,
    val name: String,
    val imageUrl: String,
    val games: Int,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val wins: Int,
    val losses: Int,
    val winPerRate: Int,
    val winPerRateToString: String
)
