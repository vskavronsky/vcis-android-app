package com.automotive.bootcamp.voicesettings.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCommand
import com.automotive.bootcamp.voicesettings.domain.repository.VoiceSettingsRepository
import com.automotive.bootcamp.voicesettings.presentation.state.VoiceSettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoiceSettingsViewModel @Inject constructor(
    private val voiceSettingsRepository: VoiceSettingsRepository
) : ViewModel() {

    private val _voiceSettingsState = MutableStateFlow(VoiceSettingsState.Empty)
    val voiceSettingsState = _voiceSettingsState.asStateFlow()

    init {
        Log.d(TAG, "init start")
        viewModelScope.launch {
            merge(
                voiceSettingsRepository.getCategories(),
                voiceSettingsRepository.getCommands()
            ).collect { stateUpdater ->
                _voiceSettingsState.update(stateUpdater)
            }
        }
    }

    fun deleteCommand(command: VoiceCommand) {
        Log.d(TAG, "command to delete: ${command.command}")
        viewModelScope.launch {
            voiceSettingsRepository.deleteCommand(command)
        }
    }

    private companion object {
        val TAG = VoiceSettingsViewModel::class.java.name
    }
}
