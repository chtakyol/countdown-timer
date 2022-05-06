package com.oolong.countdown_timer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oolong.countdown_timer.presentation.countdown_screen.CountdownScreen
import com.oolong.countdown_timer.presentation.countdown_screen.components.Timer
import com.oolong.countdown_timer.ui.theme.CountdowntimerTheme
import com.oolong.countdown_timer.utils.Constants.ACTION_START_OR_RESUME_SERVICE
import com.oolong.countdown_timer.utils.startCountdownService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountdowntimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CountdownScreen(){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startCountdownService(ACTION_START_OR_RESUME_SERVICE, it)
                        }
                    }
                }
            }
        }
    }
}
