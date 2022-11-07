package com.a90ms.domain.usecase

import com.a90ms.domain.base.ParamDtoUseCase
import com.a90ms.domain.data.dto.game.GameDto
import com.a90ms.domain.di.IoDispatcher
import com.a90ms.domain.repository.ApiRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class GetGamesUseCase @Inject constructor(
    private val repository: ApiRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : ParamDtoUseCase<String, List<GameDto>>(dispatcher) {
    override suspend fun execute(parameters: String): List<GameDto> {
        return repository.getGameInfoList(parameters)
    }
}
