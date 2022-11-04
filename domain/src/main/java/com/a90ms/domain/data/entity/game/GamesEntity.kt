package com.a90ms.domain.data.entity.game

data class GamesEntity(
    val champion: String,
    val spells: String,
    val items: String,
    val createDate: String,
    val gameType: String,
    val gameLength: String,
    val isWin: Boolean,
    val stats: GeneralEntity,
    val champions: String
)
