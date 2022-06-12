package com.oolong.countdown_timer.utils

object Utilities {
    fun getMin(duration: Long): Int {
        return (duration / 60000).toInt()
    }

    fun getSec(duration: Long): Int {
        return (duration/1000 - (duration / 60000) * 60).toInt()
    }

    fun getStringWithZeroAtHead(value: Int): String {
        return if (value > 9) {
            "$value"
        } else {
            "0$value"
        }
    }

    fun getNotificationText(duration: Long): String {
        return if (getMin(duration) > 9 && getSec(duration) > 9) {
            "${getMin(duration)}:${getSec(duration)}"
        } else if (getMin(duration) > 9 && getSec(duration) < 10) {
            "${getMin(duration)}:0${getSec(duration)}"
        } else if (getMin(duration) < 10 && getSec(duration) > 10) {
            "0${getMin(duration)}:${getSec(duration)}"
        } else {
            "0${getMin(duration)}:0${getSec(duration)}"
        }
    }
}