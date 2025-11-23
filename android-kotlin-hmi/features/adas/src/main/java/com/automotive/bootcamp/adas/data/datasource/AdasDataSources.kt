package com.automotive.bootcamp.adas.data.datasource

import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType
import com.automotive.bootcamp.adas.domain.repository.error.AdasError
import kotlinx.coroutines.flow.Flow

interface AdasDataSource {
    suspend fun getFeatures(): List<AdasFeatureType>
    suspend fun getFeatureSettings(type: AdasFeatureType): Flow<AdasFeatureSettings>
    suspend fun setFeatureSettings(type: AdasFeatureType, settings: AdasFeatureSettings): AdasError?

    suspend fun toggleFeatureState(type: AdasFeatureType): AdasError?

    suspend fun setFeatureMode(
        type: AdasFeatureType,
        mode: AdasFeatureSettings.OperatingMode,
    ): AdasError?
}

interface AdasFeatureDataSource {
    suspend fun getSettings(): Flow<AdasFeatureSettings>
    suspend fun setSettings(settings: AdasFeatureSettings): AdasError?

    suspend fun toggleState(): AdasError?
    suspend fun setMode(mode: AdasFeatureSettings.OperatingMode): AdasError?
}