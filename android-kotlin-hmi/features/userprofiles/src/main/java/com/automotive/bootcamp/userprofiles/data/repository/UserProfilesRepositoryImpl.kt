package com.automotive.bootcamp.userprofiles.data.repository

import com.automotive.bootcamp.userprofiles.data.datasource.ConfigurationDataSource
import com.automotive.bootcamp.userprofiles.data.datasource.UserDataSource
import com.automotive.bootcamp.userprofiles.data.datasource.WarningsDataSource
import com.automotive.bootcamp.userprofiles.domain.model.ConfigurationInfo
import com.automotive.bootcamp.userprofiles.domain.model.User
import com.automotive.bootcamp.userprofiles.domain.model.UserId
import com.automotive.bootcamp.userprofiles.domain.model.Warnings
import com.automotive.bootcamp.userprofiles.domain.repository.UserProfilesRepository
import com.automotive.bootcamp.userprofiles.domain.repository.error.UserProfilesError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import utils.Either
import utils.toError
import utils.toSuccess
import javax.inject.Inject
//
class UserProfilesRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userDataSource: UserDataSource,
    private val warningsDataSource: WarningsDataSource,
    private val configurationDataSource: ConfigurationDataSource
) : UserProfilesRepository {

    private val _userProfilesFlow = MutableStateFlow(0)

    override val userProfilesFlow: Flow<Int>
        get() = _userProfilesFlow
            .map { it * it }

    override fun userProfilesFunction(data: Int) {
        _userProfilesFlow.update { data }
    }

    override suspend fun userProfilesSuspendFunction(data: Int): String =
        withContext(dispatcher) {
            return@withContext data.toString()
        }

    override suspend fun userProfilesResult(data: Int): Either<String, UserProfilesError> {
        return if (data % 2 == 0) {
//            Either.Success("Great success!")
            "Great success!".toSuccess() // optional, we may use some wrapper
        } else {
//            Either.Error(UserProfilesError.EXAMPLE_ERROR)
            UserProfilesError.EXAMPLE_ERROR.toError() // optional, we may use some wrapper
        }
    }

    override suspend fun getUsers(): Flow<List<User>> = flow {
        emit(userDataSource.getUsers())
    }.flowOn(dispatcher)

    override suspend fun getWarnings(): Flow<List<Warnings>> = flow {
        emit(warningsDataSource.getWarnings())
    }.flowOn(dispatcher)

    override suspend fun getConfigurationInfo(): Flow<Map<UserId, ConfigurationInfo>> =
        configurationDataSource.getConfigurationInfoFlow().flowOn(dispatcher)
}
