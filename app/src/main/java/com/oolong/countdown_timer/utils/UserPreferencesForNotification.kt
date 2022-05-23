package com.oolong.countdown_timer.utils

data class UserPreferencesForNotification(
    val duration: Long,
    val showNotificationState: Boolean,
    val notificationSoundState: Boolean
)
