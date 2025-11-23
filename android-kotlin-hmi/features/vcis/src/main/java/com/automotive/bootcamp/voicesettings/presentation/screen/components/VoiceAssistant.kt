package com.automotive.bootcamp.voicesettings.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.automotive.bootcamp.voicesettings.R
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin16
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin8
import com.automotive.bootcamp.voicesettings.theme.ContainerPadding
import com.automotive.bootcamp.voicesettings.theme.ContainerRadius
import com.automotive.bootcamp.voicesettings.theme.ContainerSpace12
import com.automotive.bootcamp.voicesettings.theme.ContainerSpace8
import com.automotive.bootcamp.voicesettings.theme.IconListeningColor
import com.automotive.bootcamp.voicesettings.theme.IconMicBg
import com.automotive.bootcamp.voicesettings.theme.IconPadding12
import com.automotive.bootcamp.voicesettings.theme.IconRadius16
import com.automotive.bootcamp.voicesettings.theme.TextType
import compose_theme.theme.BackgroundLight
import compose_theme.theme.IntelliAutoHMITheme

@Composable
fun VoiceAssistant() {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .padding(
                start = ContainerMargin16,
                end = ContainerMargin16,
                top = ContainerMargin16,
                bottom = ContainerMargin8
            )
            .fillMaxWidth()
            .background(color = BackgroundLight, shape = RoundedCornerShape(ContainerRadius))
            .padding(ContainerPadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = ContainerSpace12,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconWithBg(
                iconId = R.drawable.baseline_mic_32,
                description = "Microphone icon",
                padding = IconPadding12,
                cornerRadius = IconRadius16,
                bgColor = IconMicBg
            )
            Column {
                Text(
                    text = "Voice Assistant Active",
                    style = TextType.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Say \"Hey Car\" to activate commands",
                    style = TextType.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(
                space = ContainerSpace8,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_circle_16),
                contentDescription = "Circle icon",
                tint = Color.Unspecified
            )
            Text(
                text = "Listening",
                style = TextType.titleSmall,
                color = IconListeningColor,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    device = Devices.PIXEL_TABLET
)
@Composable
fun VoiceAssistantPreview() {
    IntelliAutoHMITheme {
        Surface {
            VoiceAssistant()
        }
    }
}
