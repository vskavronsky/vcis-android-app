package com.automotive.bootcamp.voicesettings.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCommand
import com.automotive.bootcamp.voicesettings.presentation.screen.components.CommonVoiceCommands
import com.automotive.bootcamp.voicesettings.presentation.screen.components.QuickActions
import com.automotive.bootcamp.voicesettings.presentation.screen.components.VoiceAssistant
import com.automotive.bootcamp.voicesettings.presentation.screen.components.VoiceCategories
import com.automotive.bootcamp.voicesettings.presentation.screen.components.VoiceSensitivity
import com.automotive.bootcamp.voicesettings.presentation.state.VoiceSettingsState
import com.automotive.bootcamp.voicesettings.theme.FeatureContainerRadius
import compose_theme.theme.MintSecondary

@Composable
fun VoiceSettingsScreen(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    voiceSettingsViewModel: VoiceSettingsViewModel = hiltViewModel()
) {
    val voiceSettingsState by voiceSettingsViewModel.voiceSettingsState.collectAsStateWithLifecycle()

    BackHandler {
        onClose()
    }

    VoiceSettingsView(
        modifier = modifier.fillMaxSize(),
        state = voiceSettingsState,
        onDeleteCommand = { command: VoiceCommand -> voiceSettingsViewModel.deleteCommand(command) }
    )
}

@Composable
fun VoiceSettingsView(
    modifier: Modifier = Modifier,
    state: VoiceSettingsState,
    onDeleteCommand: (VoiceCommand) -> Unit
) {
    Surface(
        modifier = modifier,
        color = MintSecondary,
        shape = RoundedCornerShape(
            topStart = FeatureContainerRadius,
            topEnd = FeatureContainerRadius
        )
    ) {
        Column {
            VoiceAssistant()
            VoiceCategories(state)
            CommonVoiceCommands(
                modifier = Modifier.weight(1f),
                state = state,
                onDeleteCommand = onDeleteCommand
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ) {
                VoiceSensitivity(
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxHeight()
                )
                QuickActions(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }
        }
    }
}
