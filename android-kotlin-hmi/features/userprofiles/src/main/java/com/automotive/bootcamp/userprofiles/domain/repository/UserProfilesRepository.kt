package com.automotive.bootcamp.userprofiles.domain.repository

import com.automotive.bootcamp.userprofiles.domain.model.ConfigurationInfo
import com.automotive.bootcamp.userprofiles.domain.model.User
import com.automotive.bootcamp.userprofiles.domain.model.UserId
import com.automotive.bootcamp.userprofiles.domain.model.Warnings
import com.automotive.bootcamp.userprofiles.domain.repository.error.UserProfilesError
import kotlinx.coroutines.flow.Flow
import utils.Either

interface UserProfilesRepository {
    val userProfilesFlow: Flow<Int>

    fun userProfilesFunction(data: Int)

    suspend fun userProfilesSuspendFunction(data: Int): String

    suspend fun userProfilesResult(data: Int): Either<String, UserProfilesError>

    // Suspending functions to get each data type separately
    suspend fun getUsers(): Flow<List<User>>

    suspend fun getWarnings(): Flow<List<Warnings>>

    suspend fun getConfigurationInfo(): Flow<Map<UserId, ConfigurationInfo>>
}
