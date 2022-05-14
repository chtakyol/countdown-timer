package com.oolong.countdown_timer.presentation.settings_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsCardItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Default.VolumeUp,
    cardHeader: String = "Card Header",
    cardExplanation: String = "Explanation",
    isChecked: Boolean = true,
    isDark: Boolean = false,
    onClick: (Boolean) -> Unit
){
    val color = if(isDark) {
        Color.White
    } else {
        Color.Black
    }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Icon(
                imageVector = imageVector,
                contentDescription = "Card item icon",
                tint = color
            )
            Column(
                modifier = modifier
            ) {
                Text(
                    text = cardHeader,
                    color = color
                )
                Text(
                    text = cardExplanation,
                    color = color
                )
            }
        }
        Switch(
            checked = isChecked,
            onCheckedChange = {
                val emittedCheckedState = !isChecked
                onClick(emittedCheckedState)
            }
        )

//        IconButton(
//            onClick = onClick
//        ) {
//            Icon(
//                imageVector = buttonIconImageVector,
//                contentDescription = "Buttons icon"
//            )
//        }
    }
}

@Composable
@Preview
fun PreviewSettingsCardItem() {
    SettingsCardItem() {
        
    }
}