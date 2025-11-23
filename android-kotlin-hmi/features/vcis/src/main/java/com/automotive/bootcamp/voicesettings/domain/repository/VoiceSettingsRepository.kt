package com.automotive.bootcamp.voicesettings.domain.repository

import com.automotive.bootcamp.voicesettings.domain.model.VoiceCommand
import com.automotive.bootcamp.voicesettings.presentation.state.VoiceSettingsState
import kotlinx.coroutines.flow.Flow

interface VoiceSettingsRepository {
    suspend fun getCategories(): Flow<(VoiceSettingsState) -> VoiceSettingsState>

    suspend fun getCommands(): Flow<(VoiceSettingsState) -> VoiceSettingsState>
    suspend fun deleteCommand(command: VoiceCommand)
}
