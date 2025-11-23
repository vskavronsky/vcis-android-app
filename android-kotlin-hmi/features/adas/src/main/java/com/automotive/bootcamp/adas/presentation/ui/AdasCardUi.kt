package com.automotive.bootcamp.adas.presentation.ui
import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType

data class AdasCardUi(
    val active: Boolean,
    val mode: OperationModeUi,
)

fun AdasFeatureSettings.asCardUi(): AdasCardUi {
    return AdasCardUi( active = this.enabled, mode = this.getOperatingModeUi())
}