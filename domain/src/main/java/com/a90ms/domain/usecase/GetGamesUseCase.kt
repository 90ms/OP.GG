package com.a90ms.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.a90ms.common.PAGING_COUNT
import com.a90ms.domain.base.NoParamDtoUseCase
import com.a90ms.domain.data.dto.game.GameDto
import com.a90ms.domain.data.dto.game.GameResponseDto
import com.a90ms.domain.di.IoDispatcher
import com.a90ms.domain.pagingsource.GamePagingSource
import com.a90ms.domain.repository.ApiRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetGamesUseCase @Inject constructor(
    private val repository: ApiRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : NoParamDtoUseCase<Flow<PagingData<Pair<GameDto, GameResponseDto>>>>(dispatcher) {
    override suspend fun execute() = Pager(
        config = PagingConfig(
            pageSize = PAGING_COUNT / 2,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            GamePagingSource(repository)
        }
    ).flow
}
