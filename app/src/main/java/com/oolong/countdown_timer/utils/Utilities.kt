package com.oolong.countdown_timer.utils

object Utilities {
    fun getMin(duration: Long): Int {
        return (duration / 60000).toInt()
    }

    fun getSec(duration: Long): Int {
        return (duration/1000 - (duration / 60000) * 60).toInt()
    }

    fun getNotificationText(duration: Long): String {
        return "${getMin(duration)}:${getSec(duration)}"
    }
}