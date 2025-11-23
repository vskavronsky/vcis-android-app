package com.automotive.bootcamp.voicesettings.data.repository

import android.util.Log
import com.automotive.bootcamp.voicesettings.data.datasource.VoiceCategoryDataSource
import com.automotive.bootcamp.voicesettings.data.datasource.VoiceCommandDataSource
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCommand
import com.automotive.bootcamp.voicesettings.domain.repository.VoiceSettingsRepository
import com.automotive.bootcamp.voicesettings.presentation.state.VoiceSettingsState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VoiceSettingsRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val voiceCategoryDataSource: VoiceCategoryDataSource,
    private val voiceCommandDataSource: VoiceCommandDataSource
) : VoiceSettingsRepository {

    override suspend fun getCategories() =
        withContext(dispatcher) {
            Log.d(TAG, "start getting categories")
            voiceCategoryDataSource.getCategories().map { categoryList ->
                { state: VoiceSettingsState ->
                    state.copy(voiceCategories = categoryList)
                }
            }
        }

    override suspend fun getCommands() =
        withContext(dispatcher) {
            Log.d(TAG, "start getting commands")
            voiceCommandDataSource.getCommands().map { commandList ->
                { state: VoiceSettingsState ->
                    state.copy(voiceCommands = commandList)
                }
            }
        }

    override suspend fun deleteCommand(command: VoiceCommand) =
        withContext(dispatcher) {
            Log.d(TAG, "start deleting command: ${command.command}")
            voiceCommandDataSource.deleteCommand(command)
        }

    private companion object {
        val TAG = VoiceSettingsRepositoryImpl::class.java.name
    }
}
