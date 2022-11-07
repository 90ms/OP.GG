package com.a90ms.common.utils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import androidx.recyclerview.widget.RecyclerView
import com.a90ms.common.ext.px

open class RecyclerViewDividerDecoration : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        color = Color.parseColor("#ebeef1")
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart
        val right = parent.width - parent.paddingEnd
        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            if (parent.getChildAdapterPosition(child) < 0) continue
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + 5f.px
            c.drawRect(left.toFloat(), top, right.toFloat(), bottom, paint)
        }
    }
}