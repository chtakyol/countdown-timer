package com.oolong.countdown_timer.presentation.countdown_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.countdown_timer.domain.repository.UserPreferencesRepository
import com.oolong.countdown_timer.services.CountdownService
import com.oolong.countdown_timer.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountdownScreenViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {
    var isRunning = CountdownService.isRunning
    var duration = CountdownService.durationInMillis
    var showNotificationState by mutableStateOf(true)
    var muteNotificationState by mutableStateOf(true)

    var darkThemeState by mutableStateOf(false)

    init {
        getUserPreferences()
    }

    fun onClick(){
        CountdownService.isRunning.value = !CountdownService.isRunning.value
    }

    private fun getUserPreferences() {
        viewModelScope.launch {
            userPreferencesRepository.getBoolean(Constants.SHOW_NOTIFICATION).let {
                if (it != null) {
                    showNotificationState = it
                }
            }

            userPreferencesRepository.getBoolean(Constants.MUTE_NOTIFICATION_SOUND).let {
                if (it != null) {
                    muteNotificationState = it
                }
            }
            userPreferencesRepository.getBoolean(Constants.DARK_THEME_ENABLE).let {
                if (it != null) {
                    darkThemeState = it
                }
            }
        }
    }
}