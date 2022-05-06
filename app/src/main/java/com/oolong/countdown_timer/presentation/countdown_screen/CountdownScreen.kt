package com.oolong.countdown_timer.presentation.countdown_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oolong.countdown_timer.presentation.countdown_screen.components.StartStopButton
import com.oolong.countdown_timer.presentation.countdown_screen.components.Timer
import com.oolong.countdown_timer.services.CountdownService

@Composable
fun CountdownScreen(
    viewModel: CountdownScreenViewModel = hiltViewModel(),
    onStartStopClick: (Int) -> Unit
){
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
                sec = viewModel.sec.value
            )
            StartStopButton(
                isRunning =  viewModel.isRunning.value,
                onClick = {
                    onStartStopClick(20)
                    viewModel.onClick()
                }
            )
        }
    }

}