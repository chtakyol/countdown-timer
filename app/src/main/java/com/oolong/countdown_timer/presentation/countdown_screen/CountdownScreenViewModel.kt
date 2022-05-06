package com.oolong.countdown_timer.presentation.countdown_screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.countdown_timer.services.CountdownService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountdownScreenViewModel @Inject constructor(

): ViewModel() {
    var isRunning = CountdownService.isRunning
    var sec = CountdownService.sec

    init {
    }

    fun onClick(){
        CountdownService.isRunning.value = !CountdownService.isRunning.value
    }
}