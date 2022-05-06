package com.oolong.countdown_timer.services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.oolong.countdown_timer.MainActivity
import com.oolong.countdown_timer.utils.Constants.ACTION_PAUSE_SERVICE
import com.oolong.countdown_timer.utils.Constants.ACTION_START_OR_RESUME_SERVICE
import com.oolong.countdown_timer.utils.Constants.ACTION_STOP_SERVICE
import com.oolong.countdown_timer.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.oolong.countdown_timer.utils.Constants.NOTIFICATION_CHANNEL_NAME
import com.oolong.countdown_timer.utils.Constants.NOTIFICATION_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountdownService: LifecycleService() {

    companion object {
        val isRunning = mutableStateOf(false)
        val min = mutableStateOf(59)
        val sec = mutableStateOf(0)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    Log.d("Service", "Service start or resume")
                    setTimerDuration(it.getIntExtra("duration", 0))
                    startCountdownForegroundService()
                    startTimer()
                }
                ACTION_PAUSE_SERVICE -> {
                    Log.d("Service", "Service pause")
                }
                ACTION_STOP_SERVICE -> {
                    Log.d("Service", "Service stop")
                }
                else -> {
                    Log.d("Service", "Unexpected command")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun setTimerDuration(duration: Int) {
        sec.value = duration
    }

    private fun startTimer() {
        CoroutineScope(Dispatchers.Main).launch {
            while(sec.value > 0 && isRunning.value) {
                Log.d("CountdownService", sec.value.toString())
                sec.value -= 1
                delay(1000)
            }
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MainActivity::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    private fun startCountdownForegroundService() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(
            this,
            NOTIFICATION_CHANNEL_ID
        )
            .setAutoCancel(false)
            .setOngoing(true)
//            .setSmallIcon(R.drawable.ic_baseline_alarm_24)
            .setContentTitle("Background Timer")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(
            NOTIFICATION_ID,
            notificationBuilder.build()
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
}