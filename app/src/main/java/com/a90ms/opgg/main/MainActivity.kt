package com.a90ms.opgg.main

import android.os.Bundle
import androidx.activity.viewModels
import com.a90ms.opgg.R
import com.a90ms.opgg.base.BaseActivity
import com.a90ms.opgg.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupData()
    }

    private fun setupBinding() {
        with(binding) {
            vm = viewModel
        }
    }

    private fun setupData() {
        viewModel.fetchData()
    }
}
