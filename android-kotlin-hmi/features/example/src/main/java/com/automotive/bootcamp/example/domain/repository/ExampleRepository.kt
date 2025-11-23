package com.automotive.bootcamp.example.domain.repository

import com.automotive.bootcamp.example.domain.model.TpmsInfo
import com.automotive.bootcamp.example.domain.model.User
import com.automotive.bootcamp.example.domain.model.Warnings
import com.automotive.bootcamp.example.domain.repository.error.ExampleError
import kotlinx.coroutines.flow.Flow
import utils.Either

interface ExampleRepository {
    val exampleFlow: Flow<Int>

    fun exampleFunction(data: Int)

    suspend fun exampleSuspendFunction(data: Int): String

    suspend fun exampleResult(data: Int): Either<String, ExampleError>

    // Suspending functions to get each data type separately
    fun getUsers(): Flow<List<User>>

    fun getWarnings(): Flow<List<Warnings>>

    fun getTpmsInfo(): Flow<List<TpmsInfo>>
}