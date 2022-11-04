package com.a90ms.domain.data.entity.game

data class GameEntity(
    val mmr: Long,
    val champion: ChampionEntity,
    val spells: List<ImageUrlEntity?>?,
    val items: List<ImageUrlEntity?>?,
    val needRenew: Boolean,
    val gameId: String,
    val createDate: String,
    val gameLength: String,
    val gameType: String,
    val summonerId: String,
    val summonerName: String,
    val tierRankShort: String,
    val stats: StatsEntity,
    val peak: List<String>,
    val isWin: Boolean,
    val champions: List<ChampionsEntity?>?
)
