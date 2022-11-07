package com.a90ms.opgg.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.a90ms.domain.base.onError
import com.a90ms.domain.base.onException
import com.a90ms.domain.base.onSuccess
import com.a90ms.domain.data.dto.game.ChampionsDto
import com.a90ms.domain.data.dto.game.GameResponseDto
import com.a90ms.domain.data.dto.summoner.SummonerDto
import com.a90ms.domain.usecase.GetGamesUseCase
import com.a90ms.domain.usecase.GetSummonerUseCase
import com.a90ms.opgg.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSummonerUseCase: GetSummonerUseCase,
    private val getGamesUseCase: GetGamesUseCase
) : BaseViewModel() {

    private val _summonerInfo = MutableLiveData<SummonerDto>()
    val summonerInfo: LiveData<SummonerDto> get() = _summonerInfo

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> get() = _state

    fun fetchData() {
        fetchSummoner()
        fetchGames()
        isFirst.value = true
    }

    private fun fetchSummoner() {
        viewModelScope.launch {
            getSummonerUseCase().onSuccess {
                _summonerInfo.value = it
            }.onError { code, message ->
                Timber.e("fetchSummoner onError $code / $message")
            }.onException {
                Timber.e("fetchSummoner onException ${it.message}")
            }
        }
    }

    private fun fetchGames() {
        viewModelScope.launch {
            getGamesUseCase().onSuccess {
                it.map {
                    it.map {
                        if (isFirst.value == true) updateRecentData(it.second)
                        it.first
                    }
                }.cachedIn(viewModelScope).collect {
                    _state.value = MainState.OnUpdateList(it)
                }
            }.onError { code, message ->
                Timber.e("fetchGames onError $code / $message")
            }.onException {
                Timber.e("fetchGames onException ${it.message}")
            }
        }
    }

    private val isFirst = MutableLiveData(false)

    private val _winLossLabel = MutableLiveData("")
    val winLossLabel: LiveData<String> get() = _winLossLabel

    private val _kda = MutableLiveData("")
    val kda: LiveData<String> get() = _kda

    private val _mostList = MutableLiveData<List<ChampionsDto>>()
    val mostList: LiveData<List<ChampionsDto>> get() = _mostList

    private val _kdaAverage = MutableLiveData("")
    val kdaAverage: LiveData<String> get() = _kdaAverage

    private fun updateRecentData(dto: GameResponseDto) {
        val win = dto.games.count { it.isWin }
        val loss = dto.games.count { !it.isWin }

        _winLossLabel.value = "${win}승 ${loss}패"

        val kill = dto.games.map { it.stats.general.kill }.average()
        val death = dto.games.map { it.stats.general.death }.average()
        val assist = dto.games.map { it.stats.general.assist }.average()

        _kda.value = "$kill / $assist / $death"

        _mostList.value = dto.champions.sortedByDescending { it.winPerRate }

        val average = (kill + assist) / death
        val percent = (((kill + assist) / death - 1) * 100).roundToInt()

        _kdaAverage.value = "${String.format("%.2f", average)}:1 ($percent%)"

        isFirst.value = false
    }
}
