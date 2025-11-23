package com.automotive.bootcamp.voicesettings.data.datasource

import com.automotive.bootcamp.voicesettings.domain.model.VoiceCategory
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCommand
import kotlinx.coroutines.flow.Flow

interface VoiceCategoryDataSource {
    suspend fun getCategories(): Flow<List<VoiceCategory>>
}

interface VoiceCommandDataSource {
    suspend fun getCommands(): Flow<List<VoiceCommand>>
    suspend fun deleteCommand(command: VoiceCommand)
}
