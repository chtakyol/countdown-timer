package com.oolong.countdown_timer.presentation.countdown_screen

data class CountdownState(
    var min: Int = 0,
    var sec: Int = 0,
    var isRunning: Boolean = false,
    val error: String? = null
)