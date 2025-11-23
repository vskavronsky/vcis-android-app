package com.automotive.bootcamp.voicesettings.presentation.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.automotive.bootcamp.voicesettings.presentation.state.VoiceSettingsState
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin16
import com.automotive.bootcamp.voicesettings.theme.ContainerMargin8
import com.automotive.bootcamp.voicesettings.theme.ContainerSpace12

@Composable
fun VoiceCategories(state: VoiceSettingsState) {
    LazyRow(
        contentPadding = PaddingValues(
            start = ContainerMargin16,
            end = ContainerMargin16,
            top = ContainerMargin8,
            bottom = ContainerMargin8
        ),
        horizontalArrangement = Arrangement.spacedBy(ContainerSpace12)
    ) {
        items(state.voiceCategories) {
            VoiceCategoryCard(it)
        }
    }
}
