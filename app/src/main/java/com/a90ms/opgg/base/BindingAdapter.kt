package com.a90ms.opgg.base

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
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
