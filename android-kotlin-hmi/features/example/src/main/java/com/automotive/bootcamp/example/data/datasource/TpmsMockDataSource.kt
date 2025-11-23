package com.automotive.bootcamp.example.data.datasource

import com.automotive.bootcamp.example.domain.model.TpmsInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.ranges.coerceIn

class TpmsMockDataSource : TpmsDataSource {

    override suspend fun getTpmsInfo(): List<TpmsInfo> {
        // Simulate a delay for mock data retrieval
        delay(15000) // 15 seconds delay

        // Simulate fetching TPMS data from a mock data source
        return listOf(
            TpmsInfo("Front Left", 32.0),
            TpmsInfo("Front Right", 33.0),
            TpmsInfo("Rear Left", 31.5),
            TpmsInfo("Rear Right", 32.5)
        )
    }

    override fun observeTpmsInfo(): Flow<List<TpmsInfo>> = flow {
        delay(15000) // 15 seconds delay
        val initialTpmsData = listOf(
            TpmsInfo("Front Left", 32.0),
            TpmsInfo("Front Right", 33.0),
            TpmsInfo("Rear Left", 31.5),
            TpmsInfo("Rear Right", 32.5)
        )

        var currentTpmsData = initialTpmsData

        while (true) {
            val updatedTpmsData = mutableListOf<TpmsInfo>()
            for (tpmsInfo in currentTpmsData) {
                val pressureVariation = Random.nextDouble(-0.5, 0.5)
                val newPressure = tpmsInfo.pressure + pressureVariation
                val clampedPressure = newPressure.coerceIn(25.0, 40.0)
                updatedTpmsData.add(TpmsInfo(tpmsInfo.tire, clampedPressure))
            }
            currentTpmsData = updatedTpmsData
            emit(currentTpmsData)
            delay(3000) // Emit new data every 3 seconds
        }
    }
}