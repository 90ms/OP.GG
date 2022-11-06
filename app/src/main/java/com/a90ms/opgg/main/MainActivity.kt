package com.a90ms.opgg.main

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.a90ms.domain.repository.ApiRepository
import com.a90ms.opgg.R
import com.a90ms.opgg.base.BaseActivity
import com.a90ms.opgg.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var repository: ApiRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
    }

    private fun setupBinding() {
        with(binding) {
            vm = viewModel

            tv1.setOnClickListener {
                lifecycleScope.launch {
                    repository.getSummonerInfo()
                }
            }
            tv2.setOnClickListener {
                lifecycleScope.launch {
                    repository.getGameInfoList(System.currentTimeMillis().toString())
                }
            }
        }
    }
}