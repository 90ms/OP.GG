package com.a90ms.opgg.main

import androidx.paging.PagingData
import com.a90ms.domain.data.dto.game.GameDto

sealed class MainState {
    data class OnUpdateList(val pagingData: PagingData<GameDto>) : MainState()
    data class OnError(val msg: String) : MainState()
}
