package com.automotive.bootcamp.adas.data.datasource

import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType
import com.automotive.bootcamp.adas.domain.repository.error.AdasError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AdasDataSourceImpl(
    private val dataSources: Map<AdasFeatureType, AdasFeatureDataSource>,
) : AdasDataSource {

    override suspend fun getFeatures(): List<AdasFeatureType> =
        dataSources.keys.toList()

    override suspend fun getFeatureSettings(type: AdasFeatureType): Flow<AdasFeatureSettings> {
        val dataSource = dataSources[type]
            ?: throw IllegalArgumentException()

        return dataSource.getSettings()
    }

    override suspend fun setFeatureSettings(
        type: AdasFeatureType,
        settings: AdasFeatureSettings,
    ): AdasError? {
        val dataSource = dataSources[type]
            ?: return AdasError.FEATURE_NOT_AVAILABLE
        return dataSource.setSettings(settings)
    }

    override suspend fun toggleFeatureState(type: AdasFeatureType): AdasError? {
        val dataSource = dataSources[type]
            ?: return AdasError.FEATURE_NOT_AVAILABLE

        return dataSource.toggleState()
    }

    override suspend fun setFeatureMode(
        type: AdasFeatureType,
        mode: AdasFeatureSettings.OperatingMode,
    ): AdasError? {
        val dataSource = dataSources[type]
            ?: return AdasError.FEATURE_NOT_AVAILABLE

        return dataSource.setMode(mode)
    }
}