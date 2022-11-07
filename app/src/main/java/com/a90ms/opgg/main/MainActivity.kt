package com.a90ms.opgg.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.PagerSnapHelper
import com.a90ms.common.utils.RecyclerViewDecoration
import com.a90ms.domain.data.dto.game.GameDto
import com.a90ms.domain.data.dto.summoner.LeagueDto
import com.a90ms.opgg.BR
import com.a90ms.opgg.R
import com.a90ms.opgg.base.BaseActivity
import com.a90ms.opgg.base.BaseListAdapter
import com.a90ms.opgg.base.BasePagingAdapter
import com.a90ms.opgg.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: BasePagingAdapter<GameDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupObserver()
        setupRecyclerView()
        setupData()
    }

    private fun setupBinding() {
        with(binding) {
            vm = viewModel
        }
    }

    private fun setupObserver() {
        viewModel.state.observe(this) {
            when (it) {
                is MainState.OnUpdateList -> {
                    lifecycleScope.launch {
                        adapter.submitData(it.pagingData)
                    }
                }
            }
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
            val diffUtil = object : DiffUtil.ItemCallback<GameDto>() {
                override fun areItemsTheSame(
                    oldItem: GameDto,
                    newItem: GameDto
                ) = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: GameDto,
                    newItem: GameDto
                ) = oldItem == newItem
            }

            addItemDecoration(RecyclerViewDecoration())

            this@MainActivity.adapter = BasePagingAdapter(
                layoutResourceId = R.layout.item_game,
                bindingItemId = BR.item,
                viewModel = mapOf(BR.vm to viewModel),
                diffUtil = diffUtil
            ).apply {
                setupSourceLoadStateListener(
                    scope = lifecycleScope,
                    scrollTop = { scrollToPosition(0) }
                )
            }
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupData() {
        viewModel.fetchData()
    }
}
