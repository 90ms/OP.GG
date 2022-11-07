package com.a90ms.domain.data.entity.summoner

import com.a90ms.common.utils.Formatter.intToString
import com.a90ms.domain.data.dto.summoner.TierRankDto

data class TierRankEntity(
    val name: String,
    val tier: String,
    val tierDivision: String,
    val string: String,
    val shortString: String,
    val division: String,
    val imageUrl: String,
    val lp: Int,
    val tierRankPoint: Int
) {
    fun toDto() = TierRankDto(
        name = name,
        tier = tier,
        tierDivision = tierDivision,
        string = string,
        shortString = shortString,
        division = division,
        imageUrl = imageUrl,
        lp = "${lp.intToString()} LP",
        tierRankPoint = tierRankPoint
    )
}
