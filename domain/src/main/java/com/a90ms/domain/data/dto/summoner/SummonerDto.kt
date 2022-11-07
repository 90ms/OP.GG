package com.a90ms.domain.data.dto.summoner

data class SummonerDto(
    val name: String,
    val level: Int,
    val profileImageUrl: String,
    val profileBorderImageUrl: String,
    val url: String,
    val leagues: List<LeagueDto>,
    val previousTiers: List<PreviousTierDto>,
    val ladderRank: LadderRankDto,
    val profileBackgroundImageUrl: String
)
