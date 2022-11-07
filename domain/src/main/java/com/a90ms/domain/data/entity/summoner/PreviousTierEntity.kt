package com.a90ms.domain.data.entity.summoner

import com.a90ms.domain.data.dto.summoner.PreviousTierDto

data class PreviousTierEntity(
    val name: String,
    val tier: String,
    val tierDivision: String,
    val string: String,
    val shortString: String,
    val division: String,
    val imageUrl: String,
    val lp: Int,
    val tierRankPoint: Int,
    val season: Int
) {
    fun toDto() = PreviousTierDto(
        name = name,
        tier = tier,
        tierDivision = tierDivision,
        string = string,
        shortString = shortString,
        division = division,
        imageUrl = imageUrl,
        lp = lp,
        tierRankPoint = tierRankPoint,
        season = season
    )
}
