package com.a90ms.domain.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.a90ms.common.PAGING_COUNT
import com.a90ms.domain.data.dto.game.GameDto
import com.a90ms.domain.repository.ApiRepository
import javax.inject.Inject

class GamePagingSource @Inject constructor(
    private val apiRepository: ApiRepository
) : PagingSource<Int, GameDto>() {
    override fun getRefreshKey(state: PagingState<Int, GameDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameDto> {
        val page = params.key ?: 1
        return try {
            val dto = apiRepository.getGameInfoList(System.currentTimeMillis().toString())
            val nextPage = page + 1
            LoadResult.Page(
                data = dto,
                prevKey = null,
                nextKey = if (dto.size < PAGING_COUNT) null else nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
