package com.automotive.bootcamp.userprofiles.data.datasource

import androidx.compose.ui.graphics.vector.ImageVector
import com.automotive.bootcamp.userprofiles.domain.model.ConfigurationInfo
import com.automotive.bootcamp.userprofiles.domain.model.NavigationFavorite
import com.automotive.bootcamp.userprofiles.domain.model.UserId
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Star
import kotlin.random.Random
import kotlin.ranges.coerceIn
import androidx.compose.ui.graphics.Color

class ConfigurationMockDataSource : ConfigurationDataSource {

    override suspend fun getConfigurationInfo(): Map<UserId, ConfigurationInfo> {
        // Simulate a delay for mock data retrieval
        delay(5000) // 5 seconds delay

        val favorites: List<NavigationFavorite> = listOf(
            NavigationFavorite(destination = "Home", icon = Icons.Rounded.Home, color = Color(0xFFBFC6F7)),
            NavigationFavorite(destination = "Office", icon = Icons.Rounded.Place, color = Color(0xFFB7E6E0)),
            NavigationFavorite(destination = "Something", icon = Icons.Rounded.Star, color = Color(0xFFFFC7D9)),
        )

        // Simulate fetching Configuration data from a mock data source
        return mapOf(
            1 to ConfigurationInfo(1, 11, 111.0, "Something1", favorites),
            2 to ConfigurationInfo(2, 22, 222.0, "Something2", favorites),
            3 to ConfigurationInfo(3, 33, 333.0, "Something3", favorites),
            4 to ConfigurationInfo(4, 44, 444.0, "Something4", favorites),
        )
    }

    override suspend fun getConfigurationInfoFlow(): Flow<Map<UserId, ConfigurationInfo>> = flow {
        delay(15000)

        val favorites: List<NavigationFavorite> = listOf(
            NavigationFavorite(destination = "Home", icon = Icons.Rounded.Home, color = Color(0xFFBFC6F7)),
            NavigationFavorite(destination = "Office", icon = Icons.Rounded.Place, color = Color(0xFFB7E6E0)),
            NavigationFavorite(destination = "Something", icon = Icons.Rounded.Star, color = Color(0xFFFFC7D9)),
        )

        val initialConfigurationData = mapOf(
            1 to ConfigurationInfo(1, 11, 111.0, "Something1", favorites),
            2 to ConfigurationInfo(2, 22, 222.0, "Something2", favorites),
            3 to ConfigurationInfo(3, 33, 333.0, "Something3", favorites),
            4 to ConfigurationInfo(4, 44, 444.0, "Something4", favorites),
        )

        while (true) {
            emit(initialConfigurationData)
            delay(30000) // Emit new data every 3 seconds
        }
    }
}
