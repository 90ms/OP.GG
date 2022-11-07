package com.a90ms.opgg.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.a90ms.domain.base.onError
import com.a90ms.domain.base.onException
import com.a90ms.domain.base.onSuccess
import com.a90ms.domain.data.dto.summoner.SummonerDto
import com.a90ms.domain.usecase.GetGamesUseCase
import com.a90ms.domain.usecase.GetSummonerUseCase
import com.a90ms.opgg.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
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
                    it
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
}
