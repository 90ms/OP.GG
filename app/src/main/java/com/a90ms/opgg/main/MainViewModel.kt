package com.a90ms.opgg.main

import androidx.lifecycle.viewModelScope
import com.a90ms.domain.base.onError
import com.a90ms.domain.base.onException
import com.a90ms.domain.base.onSuccess
import com.a90ms.domain.usecase.GetGamesUseCase
import com.a90ms.domain.usecase.GetSummonerUseCase
import com.a90ms.opgg.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSummonerUseCase: GetSummonerUseCase,
    private val getGamesUseCase: GetGamesUseCase
) : BaseViewModel() {

    fun fetchData() {
        fetchSummoner()
        fetchGames()
    }

    private fun fetchSummoner() {
        viewModelScope.launch {
            getSummonerUseCase().onSuccess {
                Timber.d("fetchSummoner onSuccess $it")
            }.onError { code, message ->
                Timber.e("fetchSummoner onError $code / $message")
            }.onException {
                Timber.e("fetchSummoner onException ${it.message}")
            }
        }
    }

    private fun fetchGames() {
        viewModelScope.launch {
            val time = System.currentTimeMillis().toString()
            getGamesUseCase(time).onSuccess {
                Timber.d("fetchGames onSuccess $it")
            }.onError { code, message ->
                Timber.e("fetchGames onError $code / $message")
            }.onException {
                Timber.e("fetchGames onException ${it.message}")
            }
        }
    }
}
