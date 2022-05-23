package com.oolong.countdown_timer.presentation.settings_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oolong.countdown_timer.presentation.countdown_screen.CountdownScreenViewModel
import com.oolong.countdown_timer.presentation.settings_screen.components.SettingsCardItem
import com.oolong.countdown_timer.utils.Screen

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsScreenViewModel = hiltViewModel(),
) {
    var textColor = Color.Black
    var backgroundColor = Color.White
    if(viewModel.darkThemeState) {
        textColor = Color.White
        backgroundColor = Color.Black
    } else {
        textColor = Color.Black
        backgroundColor = Color.White
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.CountdownScreen.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Settings",
                        tint = textColor
                    )
                }
                Text(
                    text = "Settings",
                    color = textColor
                )
            }
        },
        backgroundColor = backgroundColor
    ) {
        Column(

        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Notification",
                    color = textColor
                )
                SettingsCardItem(
                    imageVector = Icons.Default.RemoveRedEye,
                    cardHeader = "Show notification",
                    cardExplanation = "Hide or show notification",
                    isChecked = viewModel.showNotificationState,
                    isDark = viewModel.darkThemeState
                ) {
                    viewModel.showNotificationState = it
                    viewModel.onEvent(SettingsScreenEvent.ShowNotificationToggleButton)
                }
                SettingsCardItem(
                    imageVector = Icons.Default.VolumeMute,
                    cardHeader = "Mute notification",
                    cardExplanation = "Mute or un-mute notification",
                    isChecked = viewModel.muteNotificationState,
                    isDark = viewModel.darkThemeState
                ) {
                    viewModel.muteNotificationState = it
                    viewModel.onEvent(SettingsScreenEvent.NotificationSoundToggleButton)
                }
            }
            Column(
                    modifier = Modifier
                        .padding(8.dp)
                    ) {
                Text(
                    text = "Theme",
                    color = textColor
                )
                SettingsCardItem(
                    imageVector = Icons.Default.Lightbulb,
                    cardHeader = "Dark theme",
                    cardExplanation = "Enable dark theme",
                    isChecked = viewModel.darkThemeState,
                    isDark = viewModel.darkThemeState,
                    isLocked = viewModel.isDarkThemeLocked,
                    onLockedClicked = {
                        viewModel.onEvent(SettingsScreenEvent.DarkThemeWatchAdButton)
                    }
                ) {
                    viewModel.darkThemeState = it
                    viewModel.onEvent(SettingsScreenEvent.DarkThemeToggleButton)
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Premium",
                    color = textColor
                )
                SettingsCardItem(
                    imageVector = Icons.Default.Favorite,
                    cardHeader = "Get pro",
                    cardExplanation = "Get app pro version and remove ads",
                    isChecked = viewModel.proState,
                    isDark = viewModel.darkThemeState,
                ) {
                    viewModel.proState = it
                    viewModel.onEvent(SettingsScreenEvent.GetProButton)
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewSettingsScreen() {
    val navController = rememberNavController()
    SettingsScreen(navController = navController)
}