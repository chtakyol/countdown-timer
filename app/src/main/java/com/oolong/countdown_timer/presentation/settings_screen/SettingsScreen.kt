package com.oolong.countdown_timer.presentation.settings_screen

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
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
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
                        contentDescription = "Settings"
                    )
                }
                Text(
                    text = "Settings"
                )
            }
        }
    ) {
        Column(

        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "Notification")
                SettingsCardItem(
                    imageVector = Icons.Default.RemoveRedEye,
                    cardHeader = "Show notification",
                    cardExplanation = "Hide or show notification",
                    isChecked = viewModel.showNotificationState
                ) {
                    viewModel.showNotificationState = it
                    viewModel.onEvent(SettingsScreenEvent.ShowNotificationToggleButton)
                }
                SettingsCardItem(
                    imageVector = Icons.Default.VolumeMute,
                    cardHeader = "Mute notification",
                    cardExplanation = "Mute or un-mute notification",
                    isChecked = viewModel.muteNotificationState
                ) {
                    viewModel.muteNotificationState = it
                    viewModel.onEvent(SettingsScreenEvent.NotificationSoundToggleButton)
                }
            }
            Column(
                    modifier = Modifier
                        .padding(8.dp)
                    ) {
                Text(text = "Theme")
                SettingsCardItem(
                    imageVector = Icons.Default.Lightbulb,
                    cardHeader = "Dark theme",
                    cardExplanation = "Enable dark theme",
                    isChecked = viewModel.darkThemeState
                ) {
                    viewModel.darkThemeState = it
                    viewModel.onEvent(SettingsScreenEvent.DarkThemeToggleButton)
                }
                SettingsCardItem(
                    imageVector = Icons.Default.VolumeMute,
                    cardHeader = "Get blue/white theme",
                    cardExplanation = "Sets apps theme blue and white color. " +
                            "\nNeed watch add first.",
                    isChecked = viewModel.blueWhiteThemeState
                ) {
                    viewModel.onEvent(SettingsScreenEvent.BlueWhiteThemeToggleButton)
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "Premium")
                SettingsCardItem(
                    imageVector = Icons.Default.Favorite,
                    cardHeader = "Get pro",
                    cardExplanation = "Get app pro version and remove ads",
                    isChecked = viewModel.proState
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