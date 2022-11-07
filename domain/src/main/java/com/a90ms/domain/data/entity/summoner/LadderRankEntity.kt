package com.a90ms.domain.data.entity.summoner

import com.a90ms.domain.data.dto.summoner.LadderRankDto

data class LadderRankEntity(
    val rank: Long,
    val rankPercentOfTop: Int
) {
    fun toDto() = LadderRankDto(
        rank = rank,
        rankPercentOfTop = rankPercentOfTop
    )
}
