package com.automotive.bootcamp.adas.presentation.ui

import androidx.compose.runtime.Immutable
import com.automotive.bootcamp.adas.R
import com.automotive.bootcamp.adas.domain.model.AdaptiveCruiseControlSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.BlindSpotSettings
import com.automotive.bootcamp.adas.domain.model.CollisionWarningSettings
import com.automotive.bootcamp.adas.domain.model.LaneAssistSettings
import com.automotive.bootcamp.adas.presentation.ui.OperationModeUi.*

@Immutable
sealed class OperationModeUi(
    val mode: AdasFeatureSettings.OperatingMode,
    val titleId: Int,
    val strings: Map<AdasFeatureSettings.OperatingMode, Int>
) {
    class FollowingDistance(mode: AdasFeatureSettings.OperatingMode) : OperationModeUi(
        mode = mode,
        titleId = R.string.adas_acc_mode_title,
        strings = mapOf(
            AdasFeatureSettings.OperatingMode.MODE_1 to R.string.adas_acc_mode_1,
            AdasFeatureSettings.OperatingMode.MODE_2 to R.string.adas_acc_mode_2,
            AdasFeatureSettings.OperatingMode.MODE_3 to R.string.adas_acc_mode_3
        )
    )

    class SensitivityLevel(mode: AdasFeatureSettings.OperatingMode) : OperationModeUi(
        mode = mode,
        titleId = R.string.adas_lane_assist_mode_title,
        strings = mapOf(
            AdasFeatureSettings.OperatingMode.MODE_1 to R.string.adas_lane_assist_mode_1,
            AdasFeatureSettings.OperatingMode.MODE_2 to R.string.adas_lane_assist_mode_2,
            AdasFeatureSettings.OperatingMode.MODE_3 to R.string.adas_lane_assist_mode_3
        )
    )

    class AlertLevel(mode: AdasFeatureSettings.OperatingMode) : OperationModeUi(
        mode = mode,
        titleId = R.string.adas_blind_spot_mode_title,
        strings = mapOf(
            AdasFeatureSettings.OperatingMode.MODE_1 to R.string.adas_blind_spot_mode_1,
            AdasFeatureSettings.OperatingMode.MODE_2 to R.string.adas_blind_spot_mode_2
        )
    )

    class WarningTiming(mode: AdasFeatureSettings.OperatingMode) : OperationModeUi(
        mode = mode,
        titleId = R.string.adas_collision_warning_mode_title,
        strings = mapOf(
            AdasFeatureSettings.OperatingMode.MODE_1 to R.string.adas_collision_warning_mode_1,
            AdasFeatureSettings.OperatingMode.MODE_2 to R.string.adas_collision_warning_mode_2,
            AdasFeatureSettings.OperatingMode.MODE_3 to R.string.adas_collision_warning_mode_3
        )
    )
}

fun AdasFeatureSettings.getOperatingModeUi(): OperationModeUi {
    return when (this) {
        is AdaptiveCruiseControlSettings -> FollowingDistance(this.mode)
        is LaneAssistSettings -> SensitivityLevel(this.mode)
        is BlindSpotSettings -> AlertLevel(this.mode)
        is CollisionWarningSettings -> WarningTiming(this.mode)
    }
}