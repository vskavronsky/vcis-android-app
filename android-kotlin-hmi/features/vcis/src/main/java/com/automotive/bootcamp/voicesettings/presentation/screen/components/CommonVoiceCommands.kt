package com.automotive.bootcamp.voicesettings.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCommand
import com.automotive.bootcamp.voicesettings.presentation.state.VoiceSettingsState
import com.automotive.bootcamp.voicesettings.theme.AddCommandBtnBgColor
import com.automotive.bootcamp.voicesettings.theme.AddCommandBtnColor
import com.automotive.bootcamp.voicesettings.theme.BtnPadding
import com.automotive.bootcamp.voicesettings.theme.BtnRadius20
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin16
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin8
import com.automotive.bootcamp.voicesettings.theme.ContainerPadding
import com.automotive.bootcamp.voicesettings.theme.ContainerRadius
import com.automotive.bootcamp.voicesettings.theme.ContainerSpace12
import com.automotive.bootcamp.voicesettings.theme.TextType
import compose_theme.theme.BackgroundLight

@Composable
fun CommonVoiceCommands(
    modifier: Modifier,
    state: VoiceSettingsState,
    onDeleteCommand: (VoiceCommand) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(ContainerSpace12),
        modifier = modifier
            .padding(
                start = ContainerMargin16,
                end = ContainerMargin16,
                top = ContainerMargin8,
                bottom = ContainerMargin8
            )
            .background(color = BackgroundLight, shape = RoundedCornerShape(ContainerRadius))
            .padding(ContainerPadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Common Voice Commands",
                style = TextType.headlineSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = AddCommandBtnBgColor
                ),
                contentPadding = PaddingValues(BtnPadding),
                shape = RoundedCornerShape(BtnRadius20),
                onClick = {

                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add button icon",
                    tint = AddCommandBtnColor
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = "Add Command",
                    style = TextType.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = AddCommandBtnColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(ContainerSpace12)
        ) {
            items(state.voiceCommands) {
                VoiceCommandCard(it, onDeleteCommand)
            }
        }
    }
}
