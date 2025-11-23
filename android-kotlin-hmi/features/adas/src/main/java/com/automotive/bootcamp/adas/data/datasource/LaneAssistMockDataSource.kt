package com.automotive.bootcamp.adas.data.datasource

import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.LaneAssistSettings
import com.automotive.bootcamp.adas.domain.repository.error.AdasError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaneAssistMockDataSource @Inject constructor() : AdasFeatureDataSource {
    private var settings = MutableStateFlow(LaneAssistSettings(
        enabled = false,
        mode = AdasFeatureSettings.OperatingMode.MODE_2,
        assistMode = LaneAssistSettings.AssistMode.DISABLED
    )
    )

    override suspend fun getSettings(): Flow<AdasFeatureSettings> {
        delay(5000) // Initial loading delay
        return settings
    }

    override suspend fun setSettings(settings: AdasFeatureSettings): AdasError? {
        val st: LaneAssistSettings? = settings as? LaneAssistSettings
            ?: return AdasError.FEATURE_UPDATE_FAILED

        this.settings.value = st!!
        return null
    }

    override suspend fun toggleState(): AdasError? {
        val enabled = settings.value.enabled
        delay(200)
        settings.value = settings.value.copy(enabled = !enabled);
        return null
    }

    override suspend fun setMode(mode: AdasFeatureSettings.OperatingMode): AdasError? {
        if (settings.value.mode == mode) {
            return AdasError.NOTHING_TO_UPDATE
        }

        delay(100)
        settings.value = settings.value.copy(mode = mode);
        return null
    }
    }