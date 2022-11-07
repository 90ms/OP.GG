package com.a90ms.domain.data.entity.game

import com.a90ms.domain.data.dto.game.ChampionsDto
import kotlin.math.roundToInt

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
) {
    fun toDto() = ChampionsDto(
        id = id,
        key = key,
        name = name,
        imageUrl = if (!imageUrl.startsWith("http")) "https:$imageUrl" else imageUrl,
        games = games,
        kills = kills,
        deaths = deaths,
        assists = assists,
        wins = wins,
        losses = losses,
        winPerRate = winPerRate(),
        winPerRateToString = "${winPerRate()}%"
    )

    private fun winPerRate() =
        ((wins.toDouble() / (wins.toDouble() + losses.toDouble())) * 100).roundToInt()
}
