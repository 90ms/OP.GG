package com.a90ms.domain.data.entity.summoner

data class SummonerEntity(
    val name: String,
    val level: Int,
    val profileImageUrl: String,
    val profileBorderImageUrl: String,
    val url: String,
    val leagues: List<LeaguesEntity>,
    val previousTiers: List<PreviousTierEntity>,
    val ladderRank: LadderRankEntity,
    val profileBackgroundImageUrl: String
)
