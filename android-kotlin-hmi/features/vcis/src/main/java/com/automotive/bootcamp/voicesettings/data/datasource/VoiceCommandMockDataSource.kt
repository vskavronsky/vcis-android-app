package com.automotive.bootcamp.voicesettings.data.datasource

import android.util.Log
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCommand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VoiceCommandMockDataSource : VoiceCommandDataSource {
    private val _commands = MutableStateFlow<List<VoiceCommand>>(emptyList())
    val commands = _commands.asStateFlow()

    init {
        Log.d(TAG, "init commands")

        _commands.value =
            listOf(
                VoiceCommand(
                    "\"Navigate to home\"", "Takes you to your home address",
                    "Navigation"
                ),
                VoiceCommand(
                    "\"Play my driving playlist\"", "Starts your favorite driving music",
                    "Media"
                ),
                VoiceCommand(
                    "\"Set temperature to 27 degrees\"", "Adjusts cabin temperature",
                    "Climate"
                )
            )
    }

    override suspend fun getCommands(): Flow<List<VoiceCommand>> {
        Log.d(TAG, "return commands")
        return commands
    }

    override suspend fun deleteCommand(command: VoiceCommand) {
        Log.d(TAG, "delete command: ${command.command}")
        _commands.update { it -> it.filter { it != command } }
    }

    private companion object {
        val TAG = VoiceCommandMockDataSource::class.java.name
    }
}
