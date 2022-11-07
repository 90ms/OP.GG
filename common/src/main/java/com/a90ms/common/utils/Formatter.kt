package com.a90ms.common.utils

import java.text.DecimalFormat
import java.util.Date
import java.util.concurrent.TimeUnit

object Formatter {
    private val decimalFormat = DecimalFormat("#,###")

    fun Int.intToString(): String = decimalFormat.format(this)

    fun Long.convertTimeStamp(): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(this) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(this)),
            TimeUnit.MILLISECONDS.toSeconds(this) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this))
        )
    }

    fun timeToAgo(gameTime: Long): String {
        val dateDiff = Date().time - gameTime

        val minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
        val hour = TimeUnit.MILLISECONDS.toHours(dateDiff)
        val day = TimeUnit.MILLISECONDS.toDays(dateDiff)

        return when {
            minute < 60 -> "${minute}분 전"
            hour < 24 -> "${hour}시간 전"
            day >= 7 -> when {
                day >= 365 -> "${day / 365}년 전"
                day > 30 ->
                    if (day in 361..364) {
                        "${(day / 30) - 1}개월 전"
                    } else {
                        "${day / 30}개월 전"
                    }
                else -> "${day / 7}주 전"
            }
            else -> "error"
        }
    }
}
