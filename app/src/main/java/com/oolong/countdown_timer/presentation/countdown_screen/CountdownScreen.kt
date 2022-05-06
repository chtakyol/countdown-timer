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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oolong.countdown_timer.presentation.countdown_screen.components.StartStopButton
import com.oolong.countdown_timer.presentation.countdown_screen.components.Timer

@Composable
fun CountdownScreen(
    viewModel: CountdownScreenViewModel = hiltViewModel(),
    onStartStopClick: (Long) -> Unit
){
    var duration by remember {mutableStateOf(0L)}
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
                    text = "Countdown Timer"
                )
                IconButton(
                    onClick = {
                        // TODO Navigate settings screen
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Timer(
                isRunning = viewModel.isRunning.value,
                min = viewModel.getMin(viewModel.duration.value),
                sec = viewModel.getSec(viewModel.duration.value)
            ) {
                duration = it
            }
            StartStopButton(
                isRunning =  viewModel.isRunning.value,
                onClick = {
                    onStartStopClick(duration)
                    viewModel.onClick()
                }
            )
        }
    }
}