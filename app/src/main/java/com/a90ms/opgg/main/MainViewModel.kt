package com.a90ms.opgg.main

import androidx.lifecycle.viewModelScope
import com.a90ms.domain.base.onError
import com.a90ms.domain.base.onException
import com.a90ms.domain.base.onSuccess
import com.a90ms.domain.usecase.GetSummonerUseCase
import com.a90ms.opgg.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSummonerUseCase: GetSummonerUseCase
) : BaseViewModel() {

    fun fetchData() {
        fetchSummoner()
        fetchGames()
    }

    private fun fetchSummoner() {
        viewModelScope.launch {
            getSummonerUseCase().onSuccess {
                Timber.d("onSuccess $it")
            }.onError { code, message ->
                Timber.e("onError $code / $message")
            }.onException {
                Timber.e("onException ${it.message}")
            }
        }
    }

    private fun fetchGames() {
        viewModelScope.launch {
            // TODO Call GameList
        }
    }
}
