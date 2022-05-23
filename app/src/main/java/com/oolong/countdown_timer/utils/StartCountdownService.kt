package com.oolong.countdown_timer.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.oolong.countdown_timer.services.CountdownService

@RequiresApi(Build.VERSION_CODES.O)
fun Context.startCountdownService(action: String, userPreferencesForNotification: UserPreferencesForNotification) {
    Intent(this, CountdownService::class.java).also {
        it.action = action
        it.putExtra("duration", userPreferencesForNotification.duration)
        it.putExtra("showNotificationState", userPreferencesForNotification.showNotificationState)
        it.putExtra("notificationSoundState", userPreferencesForNotification.notificationSoundState)
        this.startForegroundService(it)
    }
}