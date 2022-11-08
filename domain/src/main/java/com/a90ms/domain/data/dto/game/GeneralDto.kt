package com.a90ms.domain.data.dto.game

data class GeneralDto(
    val kill: Float,
    val death: Float,
    val assist: Float,
    val opScoreBadge: OpScoreBadgeType,
    val largestMultiKillString: String,
    val contributionForKillRate: String,
    val contributionForKillRateToString: String,
    val kdsToString: String
)

enum class OpScoreBadgeType(val value: String) {
    EMPTY(""), ACE("ACE"), MVP("MVP");

    companion object {
        fun creator(type: String): OpScoreBadgeType =
            values().firstOrNull { it.value == type } ?: EMPTY
    }
}
