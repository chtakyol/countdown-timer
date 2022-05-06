package com.oolong.countdown_timer.presentation.countdown_screen

import androidx.lifecycle.ViewModel
import com.oolong.countdown_timer.services.CountdownService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountdownScreenViewModel @Inject constructor(

): ViewModel() {
    var isRunning = CountdownService.isRunning
    var duration = CountdownService.durationInMillis
//    var sec = CountdownService.durationInMillis

    init {
    }

    fun onClick(){
        CountdownService.isRunning.value = !CountdownService.isRunning.value
    }

    fun getMin(duration: Long): Int {
        return (duration / 60000).toInt()
    }

    fun getSec(duration: Long): Int {
        return (duration/1000 - (duration / 60000) * 60).toInt()
    }
}