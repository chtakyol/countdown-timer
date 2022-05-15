package com.oolong.countdown_timer.presentation.settings_screen

sealed class SettingsScreenEvent {
    object ShowNotificationToggleButton: SettingsScreenEvent()
    object NotificationSoundToggleButton: SettingsScreenEvent()
    object DarkThemeToggleButton: SettingsScreenEvent()
    object GetProButton: SettingsScreenEvent()
}
