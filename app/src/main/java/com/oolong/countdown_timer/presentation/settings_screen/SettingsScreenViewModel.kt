package com.oolong.countdown_timer.presentation.settings_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.countdown_timer.domain.repository.UserPreferencesRepository
import com.oolong.countdown_timer.utils.Constants.DARK_THEME_ENABLE
import com.oolong.countdown_timer.utils.Constants.GET_PRO_STATE
import com.oolong.countdown_timer.utils.Constants.IS_DARK_THEME_LOCKED
import com.oolong.countdown_timer.utils.Constants.MUTE_NOTIFICATION_SOUND
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {

    var muteNotificationState by mutableStateOf(true)
    var isDarkThemeLocked by mutableStateOf(true)
    var darkThemeState by mutableStateOf(false)
    var proState by mutableStateOf(false)

    init {
        getUserPreferences()
    }

    fun onEvent(event: SettingsScreenEvent) {
        when (event) {
            SettingsScreenEvent.NotificationSoundToggleButton -> {
                viewModelScope.launch {
                    userPreferencesRepository.putBoolean(
                        MUTE_NOTIFICATION_SOUND,
                        muteNotificationState
                    )
                }
            }
            SettingsScreenEvent.DarkThemeWatchAdButton -> {
                isDarkThemeLocked = false
                viewModelScope.launch {
                    userPreferencesRepository.putBoolean(
                        IS_DARK_THEME_LOCKED,
                        isDarkThemeLocked
                    )
                }
            }
            SettingsScreenEvent.DarkThemeToggleButton -> {
                viewModelScope.launch {
                    userPreferencesRepository.putBoolean(
                        DARK_THEME_ENABLE,
                        darkThemeState
                    )
                }
            }
            SettingsScreenEvent.GetProButton -> {
                viewModelScope.launch {
                    userPreferencesRepository.putBoolean(
                        GET_PRO_STATE,
                        proState
                    )
                }
            }
        }
    }

    private fun getUserPreferences() {
        viewModelScope.launch {
            userPreferencesRepository.getBoolean(MUTE_NOTIFICATION_SOUND).let {
                if (it != null) {
                    muteNotificationState = it
                }
            }

            userPreferencesRepository.getBoolean(IS_DARK_THEME_LOCKED).let {
                if (it != null) {
                    isDarkThemeLocked = it
                }
            }

            userPreferencesRepository.getBoolean(DARK_THEME_ENABLE).let {
                if (it != null) {
                    darkThemeState = it
                }
            }

            userPreferencesRepository.getBoolean(GET_PRO_STATE).let {
                if (it != null) {
                    proState = it
                }
            }
        }
    }
}