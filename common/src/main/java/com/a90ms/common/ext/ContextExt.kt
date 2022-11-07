package com.a90ms.common.ext

import android.app.Activity
import android.content.Context

fun Context?.isValidContext(): Boolean = when (this) {
    null -> false
    is Activity -> (isDestroyed || isFinishing).not()
    else -> true
}
