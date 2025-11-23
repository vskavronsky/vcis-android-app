package com.automotive.bootcamp.example.data.repository

import com.automotive.bootcamp.example.data.datasource.TpmsDataSource
import com.automotive.bootcamp.example.data.datasource.UserDataSource
import com.automotive.bootcamp.example.data.datasource.WarningsDataSource
import com.automotive.bootcamp.example.domain.model.TpmsInfo
import com.automotive.bootcamp.example.domain.model.User
import com.automotive.bootcamp.example.domain.model.Warnings
import com.automotive.bootcamp.example.domain.repository.ExampleRepository
import com.automotive.bootcamp.example.domain.repository.error.ExampleError
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

class ExampleRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userDataSource: UserDataSource,
    private val warningsDataSource: WarningsDataSource,
    private val tpmsDataSource: TpmsDataSource
) : ExampleRepository {

    private val _exampleFlow = MutableStateFlow(0)

    override val exampleFlow: Flow<Int>
        get() = _exampleFlow
            .map { it * it }

    override fun exampleFunction(data: Int) {
        _exampleFlow.update { data }
    }

    override suspend fun exampleSuspendFunction(data: Int): String =
        withContext(dispatcher) {
            return@withContext data.toString()
        }

    override suspend fun exampleResult(data: Int): Either<String, ExampleError> {
        return if (data % 2 == 0) {
//            Either.Success("Great success!")
            "Great success!".toSuccess() // optional, we may use some wrapper
        } else {
//            Either.Error(ExampleError.EXAMPLE_ERROR)
            ExampleError.EXAMPLE_ERROR.toError() // optional, we may use some wrapper
        }
    }

    override fun getUsers(): Flow<List<User>> = flow {
        emit(userDataSource.getUsers())
    }.flowOn(dispatcher)

    override fun getWarnings(): Flow<List<Warnings>> = flow {
        emit(warningsDataSource.getWarnings())
    }.flowOn(dispatcher)

    override fun getTpmsInfo(): Flow<List<TpmsInfo>> =
        tpmsDataSource.observeTpmsInfo().flowOn(dispatcher)
}