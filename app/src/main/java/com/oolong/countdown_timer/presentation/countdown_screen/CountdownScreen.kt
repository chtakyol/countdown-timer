package com.oolong.countdown_timer.presentation.countdown_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.oolong.countdown_timer.presentation.countdown_screen.components.StartStopButton
import com.oolong.countdown_timer.presentation.countdown_screen.components.Timer
import com.oolong.countdown_timer.utils.Screen
import com.oolong.countdown_timer.utils.UserPreferencesForNotification
import com.oolong.countdown_timer.utils.Utilities

@Composable
fun CountdownScreen(
    navController: NavController,
    viewModel: CountdownScreenViewModel = hiltViewModel(),
    onStartStopClick: (UserPreferencesForNotification) -> Unit
){
    var textColor = Color.Black
    var backgroundColor = Color.White
    if(viewModel.darkThemeState) {
        textColor = Color.White
        backgroundColor = Color.Black
    } else {
        textColor = Color.Black
        backgroundColor = Color.White
    }

    var duration by remember { mutableStateOf(0L) }

    val emitObject = UserPreferencesForNotification(
        duration = duration,
        showNotificationState = viewModel.showNotificationState,
        notificationSoundState = viewModel.muteNotificationState
    )
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Countdown Timer",
                    color = textColor
                )
                IconButton(
                    onClick = {
                        navController.navigate(Screen.SettingsScreen.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = textColor
                    )
                }
            }
        },
        backgroundColor = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Timer(
                isRunning = viewModel.isRunning.value,
                min = Utilities.getMin(viewModel.duration.value),
                sec = Utilities.getSec(viewModel.duration.value),
                color = textColor
            ) {
                duration = it
            }
            StartStopButton(
                color = textColor,
                isRunning =  viewModel.isRunning.value,
                onClick = {
                    onStartStopClick(emitObject)
                    viewModel.onClick()
                }
            )
        }
    }
}