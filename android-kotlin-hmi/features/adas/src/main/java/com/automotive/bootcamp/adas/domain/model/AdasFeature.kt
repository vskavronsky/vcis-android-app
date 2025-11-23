package com.automotive.bootcamp.adas.domain.model

enum class AdasFeatureType {
    ADAPTIVE_CRUISE_CONTROL,
    LANE_KEEPING_ASSIST,
    BLIND_SPOT_MONITORING,
    FORWARD_COLLISION_WARNING
}

data class AdasFeature(
    val type: AdasFeatureType,
    val settings: AdasFeatureSettings,
)

sealed interface AdasFeatureSettings {
    val enabled: Boolean
    val mode: OperatingMode

    enum class OperatingMode {
        MODE_1,
        MODE_2,
        MODE_3
    }
}



