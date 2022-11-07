package com.a90ms.domain.data.entity.game

import com.a90ms.common.utils.Formatter.convertTimeStamp
import com.a90ms.common.utils.Formatter.timeToAgo
import com.a90ms.domain.data.dto.game.GameDto

data class GameEntity(
    val mmr: Long,
    val champion: ChampionEntity,
    val spells: List<ImageUrlEntity>,
    val items: List<ImageUrlEntity>,
    val needRenew: Boolean,
    val gameId: String,
    val createDate: Long,
    val gameLength: Long,
    val gameType: String,
    val summonerId: String,
    val summonerName: String,
    val tierRankShort: String,
    val stats: StatsEntity,
    val peak: List<String>,
    val isWin: Boolean,
) {
    fun toDto() = GameDto(
        mmr = mmr,
        champion = champion.toDto(),
        spells = spells.map(ImageUrlEntity::toDto),
        items = items.map(ImageUrlEntity::toDto),
        needRenew = needRenew,
        gameId = gameId,
        createDate = createDate,
        gameLength = gameLength,
        convertGameLength = (gameLength * 1000).convertTimeStamp(),
        gameType = gameType,
        summonerId = summonerId,
        summonerName = summonerName,
        tierRankShort = tierRankShort,
        stats = stats.toDto(),
        peak = peak,
        isWin = isWin,
        ago = timeToAgo(createDate + (gameLength * 1000)),
        itemImgList = itemAddDummy(),
        accessoriesImg = items.last().imageUrl
    )

    private fun itemAddDummy() = items.dropLast(1).map { it.imageUrl }
}
