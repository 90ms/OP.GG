package com.a90ms.opgg.base

import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.a90ms.common.ext.isValidContext
import com.a90ms.common.utils.OnSingleClickListener
import com.a90ms.domain.data.dto.game.ImageUrlDto
import com.a90ms.opgg.BR
import com.a90ms.opgg.R
import com.a90ms.opgg.main.MainViewModel
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

@BindingAdapter("bindVisible")
fun View.bindVisible(show: Boolean?) {
    isVisible = show ?: false
}

@BindingAdapter("bindKdsToString")
fun TextView.bindKdsToString(kdsToString: String?) {
    if (!kdsToString.isNullOrEmpty()) {
        val sIndex = kdsToString.indexOf("/") + 1
        val eIndex = kdsToString.lastIndexOf("/")

        val spannable = SpannableString(kdsToString)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.darkish_pink)),
            sIndex,
            eIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        text = spannable
    }
}

@BindingAdapter("bindSpellList", "bindMainViewModel")
fun RecyclerView.bindSpellList(itemList: List<ImageUrlDto>, vm: MainViewModel) {
    if (adapter == null) {
        val diffUtil = object : DiffUtil.ItemCallback<ImageUrlDto>() {
            override fun areItemsTheSame(
                oldItem: ImageUrlDto,
                newItem: ImageUrlDto
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: ImageUrlDto,
                newItem: ImageUrlDto
            ) = oldItem == newItem
        }

        adapter = BaseListAdapter(
            layoutResourceId = R.layout.item_spell,
            bindingItemId = BR.item,
            viewModel = mapOf(BR.vm to vm),
            diffUtil = diffUtil
        )
    }
    bindItemList(itemList)
}

@BindingAdapter("bindRuneList", "bindMainViewModel")
fun RecyclerView.bindRuneList(itemList: List<String>, vm: MainViewModel) {
    if (adapter == null) {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ) = oldItem == newItem
        }

        adapter = BaseListAdapter(
            layoutResourceId = R.layout.item_rune,
            bindingItemId = BR.item,
            viewModel = mapOf(BR.vm to vm),
            diffUtil = diffUtil
        )
    }
    bindItemList(itemList)
}

@BindingAdapter("bindItemList", "bindMainViewModel")
fun RecyclerView.bindItemList(itemList: List<String>, vm: MainViewModel) {
    if (adapter == null) {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ) = oldItem == newItem
        }

        adapter = BaseListAdapter(
            layoutResourceId = R.layout.item_item,
            bindingItemId = BR.item,
            viewModel = mapOf(BR.vm to vm),
            diffUtil = diffUtil
        )
    }
    bindItemList(itemList)
}
