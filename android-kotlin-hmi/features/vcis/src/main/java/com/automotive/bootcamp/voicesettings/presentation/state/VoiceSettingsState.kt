package com.automotive.bootcamp.voicesettings.presentation.state

import com.automotive.bootcamp.voicesettings.domain.model.VoiceCategory
import com.automotive.bootcamp.voicesettings.domain.model.VoiceCommand

data class VoiceSettingsState(
    val voiceCategories: List<VoiceCategory>,
    val voiceCommands: List<VoiceCommand>
) {
    companion object {
        val Empty = VoiceSettingsState(voiceCategories = emptyList(), voiceCommands = emptyList())
    }
}
