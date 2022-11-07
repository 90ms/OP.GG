package com.a90ms.domain.usecase

import com.a90ms.domain.base.NoParamDtoUseCase
import com.a90ms.domain.data.dto.summoner.SummonerDto
import com.a90ms.domain.di.IoDispatcher
import com.a90ms.domain.repository.ApiRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class GetSummonerUseCase @Inject constructor(
    private val repository: ApiRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : NoParamDtoUseCase<SummonerDto>(dispatcher) {
    override suspend fun execute(): SummonerDto {
        return repository.getSummonerInfo()
    }
}
