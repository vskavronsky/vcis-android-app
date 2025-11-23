package com.automotive.bootcamp.adas.presentation.ui

import com.automotive.bootcamp.adas.R
import com.automotive.bootcamp.adas.domain.model.AdaptiveCruiseControlSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeature
import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType
import com.automotive.bootcamp.adas.domain.model.BlindSpotSettings
import com.automotive.bootcamp.adas.domain.model.CollisionWarningSettings
import com.automotive.bootcamp.adas.domain.model.LaneAssistSettings

data class AdasTileUi (
    private val active: Boolean,
    val activeTextId: Int = R.string.adas_status_active,
    val inactiveTextId: Int = R.string.adas_status_inactive,
    val stateInfoText: String
) {
    val statusTextId = if (active) activeTextId else inactiveTextId
}

fun AdasFeatureSettings.asTileUi(): AdasTileUi {
    return when (this) {
        is AdaptiveCruiseControlSettings -> AdasTileUi(
            active = this.enabled, stateInfoText = "Speed: 65mph"
        )
        is BlindSpotSettings -> AdasTileUi(
            active = this.enabled, stateInfoText = "Centered"
        )
        is CollisionWarningSettings -> AdasTileUi(
            active = this.enabled, stateInfoText = "Clear",
            activeTextId = R.string.adas_status_monitoring,
            inactiveTextId = R.string.adas_status_disabled
        )
        is LaneAssistSettings -> AdasTileUi(
            active = this.enabled, stateInfoText = "No Threats",
            activeTextId = R.string.adas_status_ready,
            inactiveTextId = R.string.adas_status_disabled
        )
    }
}