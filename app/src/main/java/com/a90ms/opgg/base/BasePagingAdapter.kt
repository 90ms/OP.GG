package com.a90ms.opgg.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.launch

class BasePagingAdapter<ITEM : Any>(
    @LayoutRes private val layoutResourceId: Int,
    private val bindingItemId: Int,
    private val viewModel: Map<Int, ViewModel>,
    diffUtil: DiffUtil.ItemCallback<ITEM>
) : PagingDataAdapter<ITEM, BaseViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(
            parent = parent,
            layoutResourceId = layoutResourceId,
            bindingItemId = bindingItemId,
            viewModel = viewModel
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    fun setupSourceLoadStateListener(
        scope: CoroutineScope,
        scrollTop: (() -> Unit)? = null,
        isError: ((String) -> Unit)? = null
    ) {
        scrollTop?.let { setupScrollTop(scope, it) }

        scope.launch {
            loadStateFlow
                .dropWhile {
                    it.refresh is LoadState.NotLoading &&
                        it.append is LoadState.NotLoading &&
                        it.prepend is LoadState.NotLoading
                }
                .distinctUntilChanged()
                .collect {
                    val errorState = it.append as? LoadState.Error
                        ?: it.prepend as? LoadState.Error
                        ?: it.refresh as? LoadState.Error
                    errorState?.let { isError?.invoke(it.error.message ?: "error") }
                }
        }
    }

    private fun setupScrollTop(
        scope: CoroutineScope,
        scrollTop: () -> Unit
    ) {
        scope.launch {
            loadStateFlow
                .dropWhile {
                    it.refresh is LoadState.NotLoading &&
                        it.append is LoadState.NotLoading &&
                        it.prepend is LoadState.NotLoading
                }
                .distinctUntilChangedBy { it.refresh }
                .collect {
                    if (it.refresh is LoadState.NotLoading) {
                        scrollTop()
                    }
                }
        }
    }
}
