package com.a90ms.opgg.base

import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.updateMarginsRelative
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.a90ms.common.ext.isValidContext
import com.a90ms.common.ext.px
import com.a90ms.common.utils.OnSingleClickListener
import com.a90ms.domain.data.dto.game.ImageUrlDto
import com.a90ms.domain.data.dto.game.PositionDto
import com.a90ms.domain.data.dto.game.PositionType
import com.a90ms.opgg.BR
import com.a90ms.opgg.R
import com.a90ms.opgg.main.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView

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

@BindingAdapter("bindKdsAverage")
fun TextView.bindKdsAverage(value: String) {
    if (value.isNotEmpty()) {
        val spannable = SpannableStringBuilder(value)
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.darkish_pink)),
            spannable.indexOf("("),
            spannable.length,
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

@BindingAdapter("bindList", "bindPlaceHolder")
fun LinearLayout.bindList(itemList: List<String>, placeHolder: Drawable) {
    if (childCount > 0) removeAllViews()
    if (itemList.size == 6) {
        itemList.forEach {
            addDynamicItemView(it, placeHolder)
        }
    } else {
        itemList.forEach { addDynamicItemView(it, placeHolder) }
        for (i in 0 until 6 - itemList.size) {
            addDynamicItemView("", placeHolder)
        }
    }
}

fun LinearLayout.addDynamicItemView(value: String, placeHolder: Drawable) {
    ShapeableImageView(
        ContextThemeWrapper(context, R.style.CircleImageViewCorner3),
        null,
        0
    ).apply {
        layoutParams = LinearLayout.LayoutParams(24.px, 24.px).apply {
            updateMarginsRelative(start = 1.px, top = 1.px, bottom = 1.px, end = 1.px)
        }
        bindImage(value, placeHolder)
    }.run {
        addView(this)
    }
}

@BindingAdapter("bindRecentPosition")
fun ImageView.bindRecentPosition(positionDto: PositionDto?) {
    positionDto?.let {
        val drawable = when (positionDto.positionType) {
            PositionType.TOP -> R.drawable.icon_lol_top
            PositionType.JUNGLE -> R.drawable.icon_lol_jng
            PositionType.MIDDLE -> R.drawable.icon_lol_mid
            PositionType.BOTTOM -> R.drawable.icon_lol_bot
            PositionType.SUPPORT -> R.drawable.icon_lol_sup
            else -> null
        }

        if (context.isValidContext()) {
            Glide.with(context).load(drawable).into(this)
        }
    }
}
