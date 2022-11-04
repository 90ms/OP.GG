package com.a90ms.domain.data.entity.summoner

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
)
