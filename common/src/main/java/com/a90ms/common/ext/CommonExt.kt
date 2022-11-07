package com.a90ms.common.ext

import android.content.res.Resources

val Float.px: Float
    get() = (this * Resources.getSystem().displayMetrics.density)