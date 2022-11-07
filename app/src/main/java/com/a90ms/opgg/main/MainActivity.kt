package com.a90ms.opgg.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.a90ms.domain.data.dto.summoner.LeagueDto
import com.a90ms.opgg.BR
import com.a90ms.opgg.R
import com.a90ms.opgg.base.BaseActivity
import com.a90ms.opgg.base.BaseListAdapter
import com.a90ms.opgg.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupRecyclerView()
        setupData()
    }

    private fun setupBinding() {
        with(binding) {
            vm = viewModel
        }
    }

    private fun setupRecyclerView() {
        binding.layoutInfo.includeLeagues.rvLeague.run {
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)

            val diffUtil = object : DiffUtil.ItemCallback<LeagueDto>() {
                override fun areItemsTheSame(
                    oldItem: LeagueDto,
                    newItem: LeagueDto
                ) = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: LeagueDto,
                    newItem: LeagueDto
                ) = oldItem == newItem
            }

            adapter = BaseListAdapter(
                layoutResourceId = R.layout.item_league,
                bindingItemId = BR.item,
                viewModel = mapOf(BR.vm to viewModel),
                diffUtil = diffUtil
            )
        }
        binding.rvGame.run {

        }
    }

    private fun setupData() {
        viewModel.fetchData()
    }
}
