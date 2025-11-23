package com.automotive.bootcamp.adas.domain.repository

import com.automotive.bootcamp.adas.domain.model.AdasFeature
import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType
import com.automotive.bootcamp.adas.domain.repository.error.AdasError
import kotlinx.coroutines.flow.Flow
import utils.Either

interface AdasRepository {
    suspend fun getFeatures(): Flow<List<AdasFeatureType>>
    suspend fun getFeatureSettings(type: AdasFeatureType): Flow<AdasFeatureSettings>

    suspend fun switchFeatureState(type: AdasFeatureType): Either<String, AdasError>

    suspend fun setFeatureMode(
        type: AdasFeatureType,
        mode: AdasFeatureSettings.OperatingMode,
    ): Either<String, AdasError>

}