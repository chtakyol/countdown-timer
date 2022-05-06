package com.oolong.countdown_timer.presentation.countdown_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Timer(
    isRunning: Boolean = false,
    min: Int = 59,
    sec: Int = 59,
    color: Color = Color.Black,
    onDurationChange: (Long) -> Unit
){

    val minState = remember { mutableStateOf(min) }
    val secState = remember { mutableStateOf(sec) }

    Column(
        
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            if (!isRunning) {
                NumberPicker(
                    state = minState,
                    range = 0..99,
                    textStyle = MaterialTheme.typography.body2
                ) {
                    onDurationChange((it * 60 + secState.value) * 1000L)
                }
            } else {
                Text(
                    text = min.toString(),
                    color = color,
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = "min".uppercase(),
                color = color,
                style = MaterialTheme.typography.body2
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            if (!isRunning) {
                NumberPicker(
                    state = secState,
                    range = 0..59,
                    textStyle = MaterialTheme.typography.body2,
                ) {
                    onDurationChange((minState.value * 60 + it) * 1000L)
                }
            } else {
                Text(
                    text = sec.toString(),
                    color = color,
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = "sec".uppercase(),
                color = color,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview
@Composable
fun PreviewTimer(){
    Timer(){}
}