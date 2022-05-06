package com.oolong.countdown_timer.presentation.countdown_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StartStopButton(
    color: Color = Color.Black,
    isRunning: Boolean = false,
    onClick:() -> Unit
) {
    val t = if (isRunning) "stop".uppercase() else "start".uppercase()

    Text(
        text = t,
        color = color,
        style = MaterialTheme.typography.button,
        modifier = Modifier
            .clickable {
                onClick()
            }
    )
}

@Preview
@Composable
fun PreviewStartStopButton(){
    StartStopButton(){

    }
}