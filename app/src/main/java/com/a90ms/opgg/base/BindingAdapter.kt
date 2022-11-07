package com.a90ms.opgg.base

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.a90ms.common.ext.isValidContext
import com.a90ms.common.utils.OnSingleClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bindImage", "bindPlaceHolder")
fun ImageView.bindImage(url: String?, placeHolder: Drawable? = null) {
    if (context.isValidContext()) {
        val options = RequestOptions().error(placeHolder).placeholder(placeHolder).centerCrop()
        Glide.with(context).load(url).apply(options).into(this)
    }
}

@BindingAdapter("bindSingleClick")
fun View.bindSingleClick(clickListener: View.OnClickListener?) {
    clickListener?.also {
        setOnClickListener(OnSingleClickListener(it))
    } ?: setOnClickListener(null)
}

@BindingAdapter("bindItemList")
fun RecyclerView.bindItemList(item: List<Any>?) {
    if (item == null) return

    @Suppress("UNCHECKED_CAST")
    (adapter as? BaseListAdapter<Any>)?.run {
        submitList(item)
    }
}

class BaseListAdapter<ITEM : Any>(
    @LayoutRes private val layoutResourceId: Int,
    private val bindingItemId: Int,
    private val viewModel: Map<Int, Any>,
    diffUtil: DiffUtil.ItemCallback<ITEM>
) : ListAdapter<ITEM, BaseViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(
            parent = parent,
            layoutResourceId = layoutResourceId,
            bindingItemId = bindingItemId,
            viewModel = viewModel
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
