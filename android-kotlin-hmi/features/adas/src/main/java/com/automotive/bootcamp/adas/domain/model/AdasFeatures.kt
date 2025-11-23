package com.automotive.bootcamp.adas.domain.model

data class AdaptiveCruiseControlSettings(
    override val enabled: Boolean,
    override val mode: AdasFeatureSettings.OperatingMode,
    val speedLimit: Int,
) : AdasFeatureSettings

data class LaneAssistSettings(
    override val enabled: Boolean,
    override val mode: AdasFeatureSettings.OperatingMode,
    val assistMode: AssistMode,
) : AdasFeatureSettings
{
    enum class AssistMode {
        DISABLED,
        DEPARTURE_WARNING,
        ACTIVE_STEERING
    }
}

data class BlindSpotSettings(
    override val enabled: Boolean,
    override val mode: AdasFeatureSettings.OperatingMode,
) : AdasFeatureSettings

data class CollisionWarningSettings(
    override val enabled: Boolean,
    override val mode: AdasFeatureSettings.OperatingMode,
    val assistMode: AssistMode,
) : AdasFeatureSettings
{
    enum class AssistMode {
        DISABLED,
        COLLISION_WARNING,
        AUTO_BRAKE_ASSISTANCE
    }
}