package com.a90ms.common.utils

import java.text.DecimalFormat

object Formatter {
    private val decimalFormat = DecimalFormat("#,###")

    fun Int.intToString(): String = decimalFormat.format(this)
}
