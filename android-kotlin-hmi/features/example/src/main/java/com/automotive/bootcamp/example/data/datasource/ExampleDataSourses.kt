package com.automotive.bootcamp.example.data.datasource

import com.automotive.bootcamp.example.domain.model.TpmsInfo
import com.automotive.bootcamp.example.domain.model.User
import com.automotive.bootcamp.example.domain.model.Warnings
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUsers(): List<User>
}

interface WarningsDataSource {
    suspend fun getWarnings(): List<Warnings>
}

interface TpmsDataSource {
    suspend fun getTpmsInfo(): List<TpmsInfo>
    fun observeTpmsInfo(): Flow<List<TpmsInfo>>
}