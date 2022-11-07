package com.a90ms.domain.data.dto.game

data class GameDto(
    val mmr: Long,
    val champion: ChampionDto,
    val spells: List<ImageUrlDto>,
    val items: List<ImageUrlDto>,
    val needRenew: Boolean,
    val gameId: String,
    val createDate: Long,
    val gameLength: Long,
    val convertGameLength: String,
    val gameType: String,
    val summonerId: String,
    val summonerName: String,
    val tierRankShort: String,
    val stats: StatsDto,
    val peak: List<String>,
    val isWin: Boolean,
    val champions: List<ChampionsDto>,
    val ago: String,
    val itemImgList: List<String>,
    val accessoriesImg: String
)
