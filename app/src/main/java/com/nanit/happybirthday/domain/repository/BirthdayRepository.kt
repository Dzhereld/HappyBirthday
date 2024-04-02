package com.nanit.happybirthday.domain.repository

import com.nanit.happybirthday.domain.entity.BirthdayEvent
import kotlinx.coroutines.flow.Flow

interface BirthdayRepository {
    suspend fun connectToServer(
        ipAddress: String,
        port: Int
    ): Result<String>

    fun observeBirthdayEvent(): Flow<Result<BirthdayEvent>>

    suspend fun disconnectFromServer()
}