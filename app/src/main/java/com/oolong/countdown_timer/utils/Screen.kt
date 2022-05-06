package com.oolong.countdown_timer.utils

sealed class Screen(
    val route: String
) {
    object CountdownScreen: Screen("countdown_screen")
    object SettingsScreen: Screen("settings_screen")
}
