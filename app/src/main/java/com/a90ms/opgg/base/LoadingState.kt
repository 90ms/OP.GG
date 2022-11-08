package com.a90ms.opgg.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoadingState {
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading
}
