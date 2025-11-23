package com.automotive.bootcamp.userprofiles.data.datasource

import com.automotive.bootcamp.userprofiles.domain.model.ConfigurationInfo
import com.automotive.bootcamp.userprofiles.domain.model.User
import com.automotive.bootcamp.userprofiles.domain.model.UserId
import com.automotive.bootcamp.userprofiles.domain.model.Warnings
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUsers(): List<User>
}

interface WarningsDataSource {
    suspend fun getWarnings(): List<Warnings>
}

interface ConfigurationDataSource {
    suspend fun getConfigurationInfo(): Map<UserId, ConfigurationInfo>
    suspend fun getConfigurationInfoFlow(): Flow<Map<UserId, ConfigurationInfo>>
}
