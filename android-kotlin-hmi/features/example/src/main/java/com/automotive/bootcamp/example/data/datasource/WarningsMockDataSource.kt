package com.automotive.bootcamp.example.data.datasource

import com.automotive.bootcamp.example.domain.model.Warnings
import kotlinx.coroutines.delay

class WarningsMockDataSource : WarningsDataSource {
    override suspend fun getWarnings(): List<Warnings> {
        // Simulate CAN query delay
        delay(500) // 0.5 second delay

        // Simulate fetching warning data from a local database
        return listOf(
            Warnings("Low tire pressure", 101),
            Warnings("Engine overheating", 202),
            Warnings("Oil level low", 303)
        )
    }
}