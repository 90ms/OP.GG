package com.a90ms.domain.data.entity.summoner

import com.a90ms.domain.data.dto.summoner.SummonerDto

data class SummonerEntity(
    val name: String,
    val level: Int,
    val profileImageUrl: String,
    val profileBorderImageUrl: String,
    val url: String,
    val leagues: List<LeagueEntity>,
    val previousTiers: List<PreviousTierEntity>,
    val ladderRank: LadderRankEntity,
    val profileBackgroundImageUrl: String
) {
    fun toDto() = SummonerDto(
        name = name,
        level = level,
        profileImageUrl = profileImageUrl,
        profileBorderImageUrl = profileBorderImageUrl,
        url = url,
        leagues = leagues.map(LeagueEntity::toDto),
        previousTiers = previousTiers.map(PreviousTierEntity::toDto),
        ladderRank = ladderRank.toDto(),
        profileBackgroundImageUrl = profileBackgroundImageUrl
    )
}
