package com.automotive.bootcamp.adas.presentation.state

import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType
import com.automotive.bootcamp.adas.presentation.ui.AdasCardUi
import com.automotive.bootcamp.adas.presentation.ui.AdasFeatureUiConfig
import com.automotive.bootcamp.adas.presentation.ui.AdasTileUi

data class AdasFeatureState(
    val type: AdasFeatureType,
    val config: AdasFeatureUiConfig,
    val tileConfig: AdasTileUi? = null,
    val cardConfig: AdasCardUi? = null,
    val onToggle: () -> Unit = {},
    val onSelectMode: (newMode: AdasFeatureSettings.OperatingMode) -> Unit = {},
)