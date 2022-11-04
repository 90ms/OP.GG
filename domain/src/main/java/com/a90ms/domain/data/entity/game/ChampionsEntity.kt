package com.a90ms.domain.data.entity.game

data class ChampionsEntity(
    val id: Long,
    val key: String,
    val name: String,
    val imageUrl: String,
    val games: Int,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val wins: Int,
    val losses: Int
)
