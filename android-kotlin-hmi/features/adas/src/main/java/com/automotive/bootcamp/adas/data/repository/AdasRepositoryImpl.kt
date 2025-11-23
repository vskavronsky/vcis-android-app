package com.automotive.bootcamp.adas.data.repository

import com.automotive.bootcamp.adas.data.datasource.AdasDataSource
import com.automotive.bootcamp.adas.domain.model.AdasFeatureSettings
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType
import com.automotive.bootcamp.adas.domain.repository.AdasRepository
import com.automotive.bootcamp.adas.domain.repository.error.AdasError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import utils.Either
import utils.toError
import utils.toSuccess
import javax.inject.Inject

class AdasRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val dataSource: AdasDataSource,
) : AdasRepository {
    override suspend fun getFeatures() = flow {
        emit(dataSource.getFeatures())
    }.flowOn(dispatcher)

    override suspend fun getFeatureSettings(type: AdasFeatureType): Flow<AdasFeatureSettings> =
        withContext(dispatcher) {
            dataSource.getFeatureSettings(type)
        }

    override suspend fun switchFeatureState(type: AdasFeatureType): Either<String, AdasError> =
        withContext(dispatcher) {
            val error = dataSource.toggleFeatureState(type)
            error?.let {
                return@withContext error.toError()
            }
            return@withContext "Feature state changed".toSuccess()
        }

    override suspend fun setFeatureMode(
        type: AdasFeatureType,
        mode: AdasFeatureSettings.OperatingMode,
    ): Either<String, AdasError> = withContext(dispatcher) {
        val error = dataSource.setFeatureMode(type, mode)
        error?.let {
            return@withContext error.toError()
        }
        return@withContext "Feature mode changed".toSuccess()
    }
}
